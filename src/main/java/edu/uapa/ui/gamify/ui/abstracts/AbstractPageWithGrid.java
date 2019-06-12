package edu.uapa.ui.gamify.ui.abstracts;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import edu.uapa.ui.gamify.ui.abstracts.base.AbstractView;
import edu.uapa.ui.gamify.utils.Tools;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.uapa.ui.gamify.views.components.PaginatorDesign;
import edu.uapa.ui.gamify.views.components.TabBarSearchDesigner;

import java.util.List;

public abstract class AbstractPageWithGrid<T> extends AbstractView {

    protected Dialog window = new Dialog(new Label("Yelson es papa"));
    private TabBarSearchDesigner searchDesigner;
    private Grid<T> grid;
    private PaginatorDesign paginator;
    private Long currentPage;
    private Long totalPage;
    private Long itemPerPage = Tools.DEFAULT_ITEMS_PER_PAGE_VALUE;

    protected AbstractPageWithGrid() {
        if (!Tools.isLogin()) {
            Tools.navigateToLogin();
        }
        setSizeFull();
        setAlignItems(Alignment.START);
        setJustifyContentMode(JustifyContentMode.START);

        initialized();
        setLanguage();
        setAction();
    }

    private void initialized() {
        currentPage = 0L;
        searchDesigner = new TabBarSearchDesigner();
        searchDesigner.getElement().getStyle().set("width", "100%");
        paginator = new PaginatorDesign();

        grid = new Grid<>();
        grid.setHeightFull();
        grid.setPageSize(itemPerPage.intValue());
        grid.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS,
                GridVariant.LUMO_ROW_STRIPES,
                GridVariant.MATERIAL_COLUMN_DIVIDERS,
                GridVariant.LUMO_COMPACT);
        grid.setMultiSort(true);

        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(true);
        layout.setSizeFull();
        layout.add(searchDesigner);
        layout.add(grid);
        layout.add(paginator);
        layout.setHorizontalComponentAlignment(Alignment.CENTER, paginator);

        add(layout);
        setSelectionMode(Grid.SelectionMode.SINGLE);
        loadInitialData();
    }

    protected void addCRUDActionGrid() {
        getGrid().addColumn(new ComponentRenderer<>(item -> {
            Button btnView = new Button(Captions.BUTTON_VIEW);
            Button btnEdit = new Button(Captions.BUTTON_EDIT);
            Button btnDelete = new Button(Captions.BUTTON_DELETE);

            btnView.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
            btnEdit.addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_TERTIARY_INLINE);
            btnDelete.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_TERTIARY_INLINE);

            btnView.addClickListener(click -> view());
            btnEdit.addClickListener(click -> edit());
            btnDelete.addClickListener(click -> delete());

            return new HorizontalLayout(btnView, btnEdit, btnDelete);
        }
        )).setKey(Captions.GRID_COLUMN_ACTION)
                .setHeader(Captions.GRID_COLUMN_ACTION)
                .setFlexGrow(0)
                .setWidth("180px")
                .setId(Captions.GRID_COLUMN_ACTION);
    }

    protected abstract void setLanguage();

    private void setAction() {
        searchDesigner.setBtnNewAction(event -> _new());
        searchDesigner.setTfSearchChangeValueAction(event -> refresh());

        paginator.setBtnFirstAction(event -> first());
        paginator.setBtnPrevious(event -> previous());
        paginator.setBtnNext(event -> next());
        paginator.setBtnLast(event -> last());
        paginator.setCbItemsPage(event -> {
            itemPerPage = Long.parseLong(event.getValue().toString());
            setTotalPage();
            first();
        });
    }

    private void loadInitialData() {
        setDataItems(getData(currentPage, itemPerPage, searchDesigner.getTfSearchValue()));
        setTotalPage();
        refreshPaginator();
    }

    private void setSelectionMode(Grid.SelectionMode selectionMode) {
        grid.setSelectionMode(selectionMode);
    }

    private void setDataItems(List<T> dataItems) {
        grid.setItems(dataItems);
    }

    private void setTotalPage() {
        this.totalPage = (long) Math.ceil(getTotalRows() / itemPerPage);
    }

    protected abstract Long getTotalRows();

    protected abstract List<T> getData(Long page, Long size, String searchValue);

    protected abstract void _new();

    protected abstract void view();

    protected abstract void edit();

    protected abstract void delete();

    protected abstract void refresh();

    protected Grid<T> getGrid() {
        return grid;
    }

    private void first() {
        currentPage = 0L;
        setDataItems(getData(currentPage, itemPerPage, searchDesigner.getTfSearchValue()));
        refreshPaginator();
    }

    private void previous() {
        currentPage--;
        setDataItems(getData(currentPage, itemPerPage, searchDesigner.getTfSearchValue()));
        refreshPaginator();
    }

    private void setPageInformation() {
        paginator.setTfPageValue((currentPage + 1) + "/" + (totalPage + 1));
    }

    private void next() {
        currentPage++;
        setDataItems(getData(currentPage, itemPerPage, searchDesigner.getTfSearchValue()));
        refreshPaginator();
    }

    private void last() {
        currentPage = totalPage;
        setDataItems(getData(currentPage, itemPerPage, searchDesigner.getTfSearchValue()));
        refreshPaginator();
    }

    private void refreshPaginator() {
        if (currentPage.equals(0L)) {
            paginator.disabledBtnFirst();
            paginator.disabledBtnPrevious();
        } else {
            paginator.enableBtnFirst();
            paginator.enableBtnPrevious();
        }

        if (currentPage.equals(totalPage)) {
            paginator.disabledBtnNext();
            paginator.disabledBtnLast();
        } else {
            paginator.enableBtnNext();
            paginator.enableBtnLast();
        }

        setPageInformation();
    }
}
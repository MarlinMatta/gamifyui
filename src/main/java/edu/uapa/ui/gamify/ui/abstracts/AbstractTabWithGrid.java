package edu.uapa.ui.gamify.ui.abstracts;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import edu.uapa.ui.gamify.utils.Tools;
import edu.uapa.ui.gamify.utils.captions.Captions;
import edu.uapa.ui.gamify.views.components.PaginatorDesign;
import edu.uapa.ui.gamify.views.components.TabBarSearchDesigner;
import edu.utesa.lib.models.dtos.security.PermissionDto;

import java.util.List;

public abstract class AbstractTabWithGrid<T> extends PageView {

    @Id("tab-bar-search-designer")
    private TabBarSearchDesigner searchDesigner;
    private Grid<T> grid;
    private PaginatorDesign paginator;
    private Long currentPage;
    private Long totalPage;
    private Long itemPerPage = Tools.DEFAULT_ITEMS_PER_PAGE_VALUE;

    protected AbstractTabWithGrid() {
        if (!Tools.isLogin()) {
            Tools.navigateToLogin();
        }
        setSizeFull();
        setAlignItems(FlexComponent.Alignment.START);
        setJustifyContentMode(FlexComponent.JustifyContentMode.START);

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
        layout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, paginator);

        add(layout);
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        loadData();
    }

    protected void addCRUDActionGrid() {
        getGrid().addColumn(new ComponentRenderer<>(item -> {
            Button btnView = new Button(Captions.BUTTON_VIEW);
            Button btnEdit = new Button(Captions.BUTTON_EDIT);
            Button btnDelete = new Button(Captions.BUTTON_DELETE);

            btnView.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
            btnEdit.addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_TERTIARY_INLINE);
            btnDelete.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_TERTIARY_INLINE);

            btnView.addClickListener(click -> view(item));
            btnEdit.addClickListener(click -> edit(item));
            btnDelete.addClickListener(click -> delete(((PermissionDto) item).getId()));

            cantView(btnView);
            cantEdit(btnEdit);
            cantDelete(btnDelete);

            return new HorizontalLayout(btnView, btnEdit, btnDelete);
        })).setKey(Captions.GRID_COLUMN_ACTION)
                .setHeader(Captions.GRID_COLUMN_ACTION)
                .setFlexGrow(0)
                .setWidth("175px")
                .setId(Captions.GRID_COLUMN_ACTION);

        cantAdd(searchDesigner.getBtnNew());
    }

    protected abstract void setLanguage();

    private void setAction() {
        searchDesigner.setBtnNewAction(event -> _new());
        searchDesigner.setTfSearchChangeValueAction(event -> first());

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

    private void loadData() {
        setDataItems(getData(currentPage, itemPerPage, searchDesigner.getTfSearchValue()));
        setTotalPage();
        refreshPaginator();
    }

    private void setDataItems(List<T> dataItems) {
        grid.setItems(dataItems);
    }

    private void setTotalPage() {
        this.totalPage = (long) Math.ceil(getTotalRows(searchDesigner.getTfSearchValue()) / itemPerPage);
    }

    protected abstract Long getTotalRows(String searchValue);

    protected abstract List<T> getData(Long page, Long size, String searchValue);

    protected abstract void _new();

    protected abstract void view(T data);

    protected abstract void edit(T data);

    protected abstract void delete(Long id);

    protected void refresh() {
        loadData();
    }

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

    protected abstract void cantAdd(Component component);

    protected abstract void cantView(Component component);

    protected abstract void cantEdit(Component component);

    protected abstract void cantDelete(Component component);
}
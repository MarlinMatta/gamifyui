package edu.uapa.ui.gamify.ui.from;

import com.vaadin.flow.component.dialog.Dialog;
import edu.uapa.ui.gamify.requests.security.UserRequests;
import edu.uapa.ui.gamify.ui.abstracts.AbstractSingleForm;
import edu.uapa.ui.gamify.views.security.UserFormDesign;
import edu.utesa.lib.models.dtos.security.UserDto;

public class UserForm extends AbstractSingleForm<UserFormDesign, UserDto> {

    public UserForm() {
        this(new UserDto(), false, true);
        setNew(true);
    }

    public UserForm(UserDto data, boolean onlyView) {
        super(new UserFormDesign(), data, onlyView, false);
        open();
    }

    private UserForm(UserDto data, boolean onlyView, boolean isNew) {
        super(new UserFormDesign(), data, onlyView, isNew);
        open();
    }

    @Override
    protected void setLanguage() {
        setWithTab(true);
        Dialog dialog = new Dialog();
//        MultiSelectionsGridDesign multiSelectionsGrid = new MultiSelectionsGridDesign();
//        multiSelectionsGrid.setItems(UserRequests.getInstance().getPermissions(getData().getId(), ""));
//
//        multiSelectionsGrid.getGridPermission().addColumn(PermissionDto::getCode).setHeader(Captions.GRID_COLUMN_CODE);
//        multiSelectionsGrid.getGridPermission().addColumn(PermissionDto::getName).setHeader(Captions.GRID_COLUMN_NAME);
//
//        multiSelectionsGrid.setExitAction(event -> dialog.close());
//        multiSelectionsGrid.setSelectionAction(event -> {
//            multiSelectionsGrid.setChange(true);
//            dialog.close();
//        });
//
//        multiSelectionsGrid.setTfFilterChangeValueAction(event -> {
//
//        });
//
//        dialog.add(multiSelectionsGrid);
//        dialog.addDialogCloseActionListener(event -> {
//            if (multiSelectionsGrid.isChange()) {
//                getData().setPermissionDtos(multiSelectionsGrid.getItemsSelect());
//            }
//        });
        getDesign().setPermissionAction(event -> dialog.open());
    }

    @Override
    protected void visualize() {
        getDesign().visualize();
    }

    @Override
    protected void restore() {
        getDesign().restore(getData());
    }

    @Override
    protected boolean validField() {
        return getDesign().validField();
    }

    @Override
    protected void collect() {
        setData(getDesign().collectData(getData()));
    }

    @Override
    protected boolean persistence() {
        return isNew() ? UserRequests.getInstance().save(getData()) : UserRequests.getInstance().update(getData());
    }
}

package edu.uapa.ui.gamify.ui.components;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;

public class GridManager<T> extends VerticalLayout {
    private Grid<T> grid;

    public GridManager(Grid<T> grid) {
        this.grid = grid;
    }

    private void initialize() {
        this.setSizeFull();
        grid.setSizeFull();
    }

    public void setSelectionMode(Grid.SelectionMode selectionMode) {
        grid.setSelectionMode(selectionMode);
    }

    public void setData(Stream<T> stream) {
        grid.setItems(stream);
    }

    public void setData(Collection<T> collection) {
        grid.setItems(collection);
    }

    public void setData(T... list) {
        grid.setItems(list);
    }

    public void setColumn(String column) {
        grid.setColumns(column);
    }

    public void removeColumn(String column) {
        grid.removeColumnByKey(column);
    }

    public Set<T> getSelectItem() {
        return grid.getSelectedItems();
    }
}

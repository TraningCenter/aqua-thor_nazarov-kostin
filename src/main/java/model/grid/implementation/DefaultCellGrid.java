package model.grid.implementation;

import model.cell.interfaces.Cell;
import model.grid.interfaces.CellGrid;
import model.parameters.Vector;

public class DefaultCellGrid implements CellGrid {

    private Cell[][] cells;

    public DefaultCellGrid(Cell[][] cells) {
        this.cells = cells;
    }

    @Override
    public Cell getCell(Vector position) {
        return cells[position.getX()][position.getY()];
    }
}

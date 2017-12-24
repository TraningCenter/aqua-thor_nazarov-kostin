package model.grid.implementation;

import model.cell.interfaces.Cell;
import model.grid.interfaces.CellGrid;
import model.parameters.Vector;

public class DefaultCellGrid implements CellGrid {

    private Cell[][] cells;
    private Vector size;

    public DefaultCellGrid(Cell[][] cells) {

        this.cells = cells;
        setSize(cells);
    }

    private void setSize(Cell[][] cells) {
        Integer height = cells.length;
        if (height<1)
            throw new UnsupportedOperationException();
        Integer width = cells[0].length;
        size = new Vector(width, height);
    }

    @Override
    public Cell getCell(Vector position) {
        return cells[position.getX()][position.getY()];
    }

    @Override
    public Vector getSize() {
        return size;
    }
}

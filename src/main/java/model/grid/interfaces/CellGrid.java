package model.grid.interfaces;

import model.cell.interfaces.Cell;
import model.parameters.Vector;

/**
 * interface for grid space of cells
 */
public interface CellGrid {
    Cell getCell(Vector position);
    Vector getSize();

}

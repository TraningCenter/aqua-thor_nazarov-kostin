package model.grid.interfaces;

import model.cell.interfaces.Cell;
import model.parameters.Vector;

public interface CellGrid {
    Cell getCell(Vector position);
}

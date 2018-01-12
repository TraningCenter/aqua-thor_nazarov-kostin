package model.ocean.interfaces;

import model.cell.interfaces.RelativeCell;
import model.grid.interfaces.CellGrid;
import model.ocean.implementaion.DefaultOcean;
import model.parameters.Vector;

import java.util.List;

/**
 * interface for cells behavior, manages different physical conditions
 */
public interface CellsBehavior {
    List<RelativeCell> getCellsInRange(CellGrid cellGrid, Vector position, Integer range);
    Vector getNewPosition(CellGrid cellGrid, Vector position, Vector direction);
}

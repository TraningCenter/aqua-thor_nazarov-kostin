package model.ocean.interfaces;

import model.cell.interfaces.RelativeCell;
import model.grid.interfaces.CellGrid;
import model.ocean.implementaion.DefaultOcean;
import model.parameters.Vector;

import java.util.List;

public interface CellsBehavior {
    List<RelativeCell> getCellsInRange(CellGrid cellGrid, Vector position, Integer range);
    void resolveBorderCells(DefaultOcean ocean);
}

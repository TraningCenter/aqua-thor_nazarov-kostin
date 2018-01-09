package model.ocean.interfaces;

import model.cell.interfaces.Cell;
import model.cell.interfaces.RelativeCell;
import model.fish.interfaces.Fish;
import model.grid.interfaces.CellGrid;
import model.parameters.Vector;

import java.util.List;

public interface OceanSpace {
    List<RelativeCell> getCellsInRange(Vector position, Integer range);
    Integer getFlowStrength(Vector position, Vector direction);
    void addFish(Fish fish);
    void removeFish(Fish fish);
    Cell getCell(Vector position);
    Vector getOceanSize();
    Vector getNewPosition(Vector position, Vector direction);
}

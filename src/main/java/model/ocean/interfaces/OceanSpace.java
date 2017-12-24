package model.ocean.interfaces;

import model.cell.interfaces.Cell;
import model.cell.interfaces.RelativeCell;
import model.fish.interfaces.Fish;
import model.parameters.Vector;

import java.util.List;

public interface OceanSpace {
    List<RelativeCell> getCellsInRange(Vector position, Integer range);
    Vector modifyVelocity(Vector baseVelocity);
    void addFish(Fish fish);
}

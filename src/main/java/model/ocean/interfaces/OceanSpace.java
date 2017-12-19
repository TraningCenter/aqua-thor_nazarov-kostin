package model.ocean.interfaces;

import model.cell.interfaces.Cell;
import model.fish.interfaces.Fish;
import model.parameters.Vector;

import java.util.List;

public interface OceanSpace {
    List<Cell> getCellsInRange(Vector position, Integer range);
    Vector modifyVecocity(Vector baseVelocity);
    void addFish(Fish fish);
}

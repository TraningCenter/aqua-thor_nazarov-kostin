package model.fish.interfaces;

import model.cell.interfaces.Cell;
import model.fish.implementation.OceanFish;
import model.ocean.interfaces.OceanSpace;

public interface EatingOceanFishStrategy {
    void eatIfNeed(OceanFish oceanFish, OceanSpace oceanSpace);
}

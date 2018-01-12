package model.fish.interfaces;

import model.fish.implementation.OceanFish;
import model.ocean.interfaces.OceanSpace;

/**
 * Eating behavior for ocean fish, tells how and which food
 */
public interface EatingOceanFishStrategy {
    void eatIfNeed(OceanFish oceanFish, OceanSpace oceanSpace);
}

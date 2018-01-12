package model.fish.interfaces;

import model.ocean.interfaces.Ocean;
import model.ocean.interfaces.OceanSpace;
import model.parameters.Vector;

/**
 * interface for reproduction behavior, creates new fishes at position
 */
public interface ReproductionBehavior {
    void birth(OceanSpace ocean, Vector birthPosition);
}

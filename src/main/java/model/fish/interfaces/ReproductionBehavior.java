package model.fish.interfaces;

import model.ocean.interfaces.Ocean;
import model.ocean.interfaces.OceanSpace;
import model.parameters.Vector;

public interface ReproductionBehavior {
    void birth(OceanSpace ocean, Vector birthPosition);
}

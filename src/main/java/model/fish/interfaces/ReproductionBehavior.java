package model.fish.interfaces;

import model.ocean.interfaces.Ocean;
import model.parameters.Vector;

public interface ReproductionBehavior {
    void birth(Ocean ocean, Vector birthPosition);
}

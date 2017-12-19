package model.fish.interfaces;

import model.fish.implementation.Target;
import model.ocean.interfaces.OceanSpace;
import model.parameters.Vector;

public interface TargetCalculationFishStrategy {
    Target calculateTarget(Vector currentPosition, OceanSpace oceanSpace);
}

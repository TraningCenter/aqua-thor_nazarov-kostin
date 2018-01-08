package model.fish.interfaces;

import model.fish.implementation.OceanFish;
import model.fish.implementation.target.Target;
import model.ocean.interfaces.OceanSpace;

public interface MoveToTargetStrategy {
    void moveToTarget(OceanFish oceanFish, OceanSpace oceanSpace, Target target);
}

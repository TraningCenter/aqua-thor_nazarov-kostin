package model.fish.interfaces;

import model.fish.implementation.OceanFish;
import model.fish.implementation.target.Target;
import model.ocean.interfaces.OceanSpace;

/**
 * interface for moving to target behavior in space
 */
public interface MoveToTargetStrategy {
    void moveToTarget(OceanFish oceanFish, OceanSpace oceanSpace, Target target);
}

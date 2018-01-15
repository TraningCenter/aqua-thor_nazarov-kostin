package model.fish.factory;

import model.fish.implementation.FishType;
import model.fish.interfaces.Fish;
import model.ocean.interfaces.OceanSpace;
import model.parameters.FishParameters;
import model.parameters.OceanType;
import model.parameters.Vector;
/**
 * interface for classes, which creates one fish with necessary parameters
 */
public interface FishFactory {
    Fish createFish(FishType fishType, FishParameters fishParameters, Vector startPosition, OceanSpace oceanSpace);
}

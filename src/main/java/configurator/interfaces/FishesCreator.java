package configurator.interfaces;

import model.fish.interfaces.Fish;
import model.ocean.interfaces.OceanSpace;
import model.parameters.OceanParameters;

import java.util.List;

/**
 * interface for classes which can create list of fishes
 */
public interface FishesCreator {
    public List<Fish> createFishes(OceanParameters oceanParameters, OceanSpace oceanSpace);
}

package configurator.interfaces;

import model.fish.interfaces.Fish;
import model.ocean.interfaces.OceanSpace;
import model.parameters.OceanParameters;

import java.util.List;

public interface FishesCreator {
    public List<Fish> createFishes(OceanParameters oceanParameters, OceanSpace oceanSpace);
}

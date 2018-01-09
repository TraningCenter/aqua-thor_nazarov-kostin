package model.fish.implementation;

import model.cell.interfaces.Cell;
import model.fish.interfaces.EatingOceanFishStrategy;
import model.ocean.interfaces.OceanSpace;

public class PassiveEatingOceanFishStrategy implements EatingOceanFishStrategy {

    @Override
    public void eatIfNeed(OceanFish oceanFish, OceanSpace oceanSpace) {
        if (oceanFish.getCurrentStarvationTimeTicks()<oceanFish.getLifeParameters().getStarvationTimeTicks()/2)
            return;

        oceanFish.changeState(new EatingOceanFishState(oceanFish));
    }
}

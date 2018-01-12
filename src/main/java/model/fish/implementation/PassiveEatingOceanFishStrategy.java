package model.fish.implementation;

import model.fish.interfaces.EatingOceanFishStrategy;
import model.ocean.interfaces.OceanSpace;

/**
 * Eating behavior for passive ocean fish, this fish will eat when current starvation is half of maximum
 */
public class PassiveEatingOceanFishStrategy implements EatingOceanFishStrategy {

    @Override
    public void eatIfNeed(OceanFish oceanFish, OceanSpace oceanSpace) {
        if (oceanFish.getCurrentStarvationTimeTicks()<oceanFish.getLifeParameters().getStarvationTimeTicks()/2)
            return;

        oceanFish.changeState(new EatingOceanFishState(oceanFish));
    }
}

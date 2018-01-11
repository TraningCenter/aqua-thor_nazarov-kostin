package model.fish.implementation;

import model.fish.interfaces.OceanFishState;
import model.parameters.FishState;

public class EatingOceanFishState implements OceanFishState {

    private int currentEatingTimeTicks=0;

    private OceanFish oceanFish;

    public EatingOceanFishState(OceanFish oceanFish) {
        this.oceanFish = oceanFish;
    }

    @Override
    public FishState getState() {
        return FishState.EATING;
    }

    @Override
    public void action() {
        if (currentEatingTimeTicks>0)
        {
            oceanFish.resetStarvationTimeTicks();
            oceanFish.changeState(new MovingOceanFishState(oceanFish));
        }

        currentEatingTimeTicks++;
    }
}

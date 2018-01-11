package model.fish.implementation;

import model.fish.interfaces.OceanFishState;
import model.parameters.FishState;

public class BirthOceanFishState implements OceanFishState {

    private OceanFish oceanFish;
    private int tickToBecomeAdult;
    private int tickCount;

    public BirthOceanFishState(OceanFish oceanFish) {

        this.oceanFish = oceanFish;
        this.tickToBecomeAdult = oceanFish.getLifeParameters().getTimeToBecomeAdultTicks();
    }

    @Override
    public FishState getState() {
        return FishState.BIRTH;
    }

    @Override
    public void action() {
        tickCount++;
        if (tickCount>=tickToBecomeAdult){
            oceanFish.changeState(new MovingOceanFishState(oceanFish));
        }
    }
}

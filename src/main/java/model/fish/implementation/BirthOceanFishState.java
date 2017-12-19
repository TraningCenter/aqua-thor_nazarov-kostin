package model.fish.implementation;

import model.fish.interfaces.OceanFishState;

public class BirthOceanFishState implements OceanFishState {

    private OceanFish oceanFish;
    private Integer tickToBecomeAdult;
    private Integer tickCount=0;

    public BirthOceanFishState(OceanFish oceanFish, Integer tickToBecomeAdult) {

        this.oceanFish = oceanFish;
        this.tickToBecomeAdult = tickToBecomeAdult;
    }

    @Override
    public void action() {
        tickCount++;
        if (tickCount>=tickToBecomeAdult){
            oceanFish.changeState(new MovingOceanFishState(oceanFish));
        }
    }
}

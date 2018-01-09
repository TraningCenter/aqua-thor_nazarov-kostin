package model.fish.implementation;

import model.fish.interfaces.OceanFishState;

public class BirthOceanFishState implements OceanFishState {

    private OceanFish oceanFish;
    private Integer tickToBecomeAdult;
    private Integer tickCount;

    public BirthOceanFishState(OceanFish oceanFish) {

        this.oceanFish = oceanFish;
        this.tickToBecomeAdult = oceanFish.getLifeParameters().getLifeTimeTicks();
    }

    @Override
    public void action() {
        tickCount++;
        if (tickCount>=tickToBecomeAdult){
            oceanFish.changeState(new MovingOceanFishState(oceanFish));
        }
    }
}

package model.fish.implementation;

import model.fish.interfaces.OceanFishState;
import model.parameters.FishState;

public class DoingNothingOceanFishState implements OceanFishState {
    @Override
    public FishState getState() {
        return FishState.BIRTH;
    }

    @Override
    public void action() {

    }
}

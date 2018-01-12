package model.fish.implementation;

import model.fish.interfaces.OceanFishState;
import model.parameters.FishState;

/**
 * Class for Ocean fish state, encapsulate doing nothing behavior, null object pattern for OceanFishState
 */
public class DoingNothingOceanFishState implements OceanFishState {
    @Override
    public FishState getState() {
        return FishState.BIRTH;
    }

    @Override
    public void action() {

    }
}

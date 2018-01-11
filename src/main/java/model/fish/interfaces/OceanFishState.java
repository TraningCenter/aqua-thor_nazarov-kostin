package model.fish.interfaces;

import model.parameters.FishState;

public interface OceanFishState {
    FishState getState();
    void action();
}

package model.fish.interfaces;

import model.parameters.FishState;

/**
 * Class for Ocean fish state, encapsulate current behavior, implements state pattern
 */
public interface OceanFishState {
    FishState getState();
    void action();
}

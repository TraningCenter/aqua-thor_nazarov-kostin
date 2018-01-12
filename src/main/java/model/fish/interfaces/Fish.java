package model.fish.interfaces;

import model.fish.implementation.FishType;
import model.parameters.FishState;
import model.parameters.Vector;

/**
 * Main interface for Fish, ocean using it for updating state
 */
public interface Fish {
    FishType getType();
    FishState getState();
    Vector getCurrentPosition();
    void setCurrentPosition(Vector position);
    void action();

}

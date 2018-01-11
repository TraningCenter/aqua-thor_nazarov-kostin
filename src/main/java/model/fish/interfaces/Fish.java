package model.fish.interfaces;

import model.fish.implementation.FishType;
import model.parameters.Vector;

public interface Fish {
    FishType getType();
    Vector getCurrentPosition();
    void setCurrentPosition(Vector position);
    void action();

}

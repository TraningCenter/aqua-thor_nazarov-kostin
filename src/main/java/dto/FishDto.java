package dto;

import model.fish.implementation.FishType;
import model.parameters.FishState;
import model.parameters.Vector;

public class FishDto {

    private FishType fishType;
    private Vector coord;
    private FishState fishState;

    public FishType getFishType() {
        return fishType;
    }

    public void setFishType(FishType fishType) {
        this.fishType = fishType;
    }

    public Vector getCoord() {
        return coord;
    }

    public void setCoord(Vector coord) {
        this.coord = coord;
    }

    public FishState getFishState() {
        return fishState;
    }

    public void setFishState(FishState fishState) {
        this.fishState = fishState;
    }
}

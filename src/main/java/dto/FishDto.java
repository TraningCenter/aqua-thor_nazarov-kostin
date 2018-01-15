package dto;

import model.fish.implementation.FishType;
import model.parameters.FishState;
import model.parameters.Vector;

import javax.xml.bind.annotation.XmlType;

/**
 * class for store special fish's parameters for rendering
 */
public class FishDto {

    private FishType fishType;
    private Vector position;
    private FishState fishState;

    public FishDto(FishType fishType, Vector position, FishState fishState) {
        this.fishType = fishType;
        this.position = position;
        this.fishState = fishState;
    }

    public FishDto() {
    }

    public FishType getFishType() {
        return fishType;
    }

    public void setFishType(FishType fishType) {
        this.fishType = fishType;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public FishState getFishState() {
        return fishState;
    }

    public void setFishState(FishState fishState) {
        this.fishState = fishState;
    }
}

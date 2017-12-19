package model.fish.implementation;

import model.fish.interfaces.Fish;
import model.fish.interfaces.OceanFishState;
import model.fish.interfaces.ReproductionBehavior;
import model.fish.interfaces.TargetCalculationFishStrategy;
import model.ocean.interfaces.OceanSpace;
import model.parameters.Vector;

public class OceanFish implements Fish {

    private OceanSpace oceanSpace;
    private OceanFishState oceanFishState;
    private FishType fishType;
    private TargetCalculationFishStrategy targetCalculationFishStrategy;
    private ReproductionBehavior reproductionBehavior;

    private Vector currentPosition;

    @Override
    public FishType getType() {
        return null;
    }

    @Override
    public Vector getCurrentPosition() {
        return null;
    }

    @Override
    public void action() {

    }

    public void changeState(OceanFishState nextFishState){

    }

    public Target calculateTargetPosition(){
        return null;
    }

    /*
    1 0  Right
    -1 0 Left
    0 1  Up
    0 -1 Down
     */
    public void move(Vector direction){

    }
}

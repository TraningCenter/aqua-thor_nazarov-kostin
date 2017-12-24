package model.fish.implementation;

import model.fish.interfaces.*;
import model.ocean.interfaces.OceanSpace;
import model.parameters.Vector;

public class OceanFish implements Fish {

    private FishType fishType;

    private Vector currentPosition;
    private OceanSpace oceanSpace;

    private OceanFishState oceanFishState;
    private TargetCalculationFishStrategy targetCalculationFishStrategy;
    private ReproductionBehavior reproductionBehavior;
    private MoveToTargetStrategy moveToTargetStrategy;

    public OceanFish(FishType fishType, Vector startPosition,
                     OceanSpace oceanSpace, OceanFishState oceanFishState,
                     TargetCalculationFishStrategy targetCalculationFishStrategy,
                     ReproductionBehavior reproductionBehavior,
                     MoveToTargetStrategy moveToTargetStrategy) {
        this.fishType = fishType;
        this.currentPosition = startPosition;
        this.oceanSpace = oceanSpace;
        this.oceanFishState = oceanFishState;
        this.targetCalculationFishStrategy = targetCalculationFishStrategy;
        this.reproductionBehavior = reproductionBehavior;
        this.moveToTargetStrategy = moveToTargetStrategy;
    }

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
        return targetCalculationFishStrategy.calculateTarget(currentPosition, oceanSpace);
    }

    public void moveToTarget(Target target){
        moveToTargetStrategy.moveToTarget(this, oceanSpace, target);
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

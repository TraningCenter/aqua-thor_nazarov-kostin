package model.fish.implementation;

import model.fish.implementation.target.Target;
import model.fish.interfaces.*;
import model.fish.interfaces.target.TargetCalculationFishStrategy;
import model.fish.interfaces.target.TargetCellPredicate;
import model.fish.interfaces.target.TargetPriorityCalcFunction;
import model.ocean.interfaces.OceanSpace;
import model.parameters.FishParameters;
import model.parameters.Vector;

public class OceanFish implements Fish {

    private FishType fishType;

    private Vector currentPosition;
    private OceanSpace oceanSpace;

    private OceanFishState oceanFishState;
    private TargetCalculationFishStrategy targetCalculationFishStrategy;
    private ReproductionBehavior reproductionBehavior;
    private MoveToTargetStrategy moveToTargetStrategy;
    private TargetCellPredicate targetCellPredicate;
    private TargetPriorityCalcFunction targetPriorityCalcFunction;

    private FishParameters lifeParameters;

    public OceanFish(FishType fishType, FishParameters fishParameters,
                     Vector startPosition,
                     OceanSpace oceanSpace, OceanFishState oceanFishState,
                     TargetCalculationFishStrategy targetCalculationFishStrategy,
                     ReproductionBehavior reproductionBehavior,
                     MoveToTargetStrategy moveToTargetStrategy,
                     TargetCellPredicate targetCellPredicate,
                     TargetPriorityCalcFunction targetPriorityCalcFunction) {
        this.fishType = fishType;
        this.lifeParameters = fishParameters;
        this.currentPosition = startPosition;
        this.oceanSpace = oceanSpace;
        this.oceanFishState = oceanFishState;
        this.targetCalculationFishStrategy = targetCalculationFishStrategy;
        this.reproductionBehavior = reproductionBehavior;
        this.moveToTargetStrategy = moveToTargetStrategy;
        this.targetCellPredicate = targetCellPredicate;
        this.targetPriorityCalcFunction = targetPriorityCalcFunction;
    }

    @Override
    public FishType getType() {
        return fishType;
    }

    @Override
    public Vector getCurrentPosition() {
        return currentPosition;
    }

    @Override
    public void setCurrentPosition(Vector position) {
        this.currentPosition=position;
    }

    @Override
    public void action() {
        this.oceanFishState.action();
    }

    public void changeState(OceanFishState nextFishState){
        this.oceanFishState = nextFishState;
    }

    public Target calculateTargetPosition(){
        return targetCalculationFishStrategy.calculateTarget(currentPosition, lifeParameters.getSmellSenseDistance(),
                oceanSpace, targetCellPredicate, targetPriorityCalcFunction);
    }

    public void moveToTarget(Target target){

        moveToTargetStrategy.moveToTarget(this, oceanSpace, target);
    }

    private Integer getCurrentTimeToMove(){

        return lifeParameters.getTimeToMoveThroughOneCell();
    }

    public Integer getTimeToMoveToPosition(Vector target){
        return getCurrentTimeToMove() + oceanSpace.getFlowStrength(currentPosition, currentPosition.minus(target));
    }

    /*
    1 0  Right
    -1 0 Left
    0 1  Up
    0 -1 Down
     */
    public void move(Vector direction){

        currentPosition=oceanSpace.getNewPosition(this.currentPosition, direction);

    }

    public FishParameters getLifeParameters(){
        return this.lifeParameters;
    }
}

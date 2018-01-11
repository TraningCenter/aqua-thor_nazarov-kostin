package model.fish.implementation;

import model.fish.implementation.target.Target;
import model.fish.interfaces.*;
import model.fish.interfaces.target.TargetCalculationFishStrategy;
import model.fish.interfaces.target.TargetCellPredicate;
import model.fish.interfaces.target.TargetPriorityCalcFunction;
import model.ocean.interfaces.OceanSpace;
import model.parameters.FishParameters;
import model.parameters.FishState;
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
    private EatingOceanFishStrategy eatingOceanFishStrategy;

    private FishParameters lifeParameters;

    private int noBirthTimeTicks = 0;
    private int starvationTimeTicks = 0;
    private int ageTimeTicks = 0;
    private int restedTimeTicks = 0;

    public OceanFish(FishType fishType, FishParameters fishParameters,
                     Vector startPosition,
                     OceanSpace oceanSpace, OceanFishState oceanFishState,
                     TargetCalculationFishStrategy targetCalculationFishStrategy,
                     ReproductionBehavior reproductionBehavior,
                     MoveToTargetStrategy moveToTargetStrategy,
                     TargetCellPredicate targetCellPredicate,
                     TargetPriorityCalcFunction targetPriorityCalcFunction,
                     EatingOceanFishStrategy eatingOceanFishStrategy) {
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
        this.eatingOceanFishStrategy = eatingOceanFishStrategy;
    }

    @Override
    public FishType getType() {
        return fishType;
    }

    @Override
    public FishState getState() {
        return this.oceanFishState.getState();
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
        if (tooOld() || starvationIsToHigh())
            die();
        
        if (canGiveBirth())
            giveBirth();

        this.oceanFishState.action();

        updateTicks();
    }

    private boolean tooOld() {
        return ageTimeTicks>=lifeParameters.getLifeTimeTicks();
    }

    private void die() {
        this.oceanSpace.removeFish(this);
    }

    private boolean starvationIsToHigh() {
        return starvationTimeTicks>=lifeParameters.getStarvationTimeTicks();
    }

    private void updateTicks() {
        noBirthTimeTicks++;
        starvationTimeTicks++;
        ageTimeTicks++;
        restedTimeTicks++;
    }

    private boolean canGiveBirth(){
        return noBirthTimeTicks>=getLifeParameters().getReproductionPeriodTicks();
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

    private Integer getCurrentTimeToMove(Vector direction){

        return lifeParameters.getTimeToMoveThroughOneCell() - oceanSpace.getFlowStrength(currentPosition, direction);
    }

    /*
    1 0  Right
    -1 0 Left
    0 1  Up
    0 -1 Down
     */
    public void move(Vector direction){

        if (!isRestedToMove(direction))
            return;

        Vector newPosition = oceanSpace.getNewPosition(this.currentPosition, direction);

        this.oceanSpace.getCell(currentPosition).removeFish(this);
        this.oceanSpace.getCell(newPosition).add(this);

        currentPosition=newPosition;

        restedTimeTicks=0;
    }

    private boolean isRestedToMove(Vector direction) {
        return restedTimeTicks >= getCurrentTimeToMove(direction);
    }

    public FishParameters getLifeParameters(){
        return this.lifeParameters;
    }

    public void giveBirth(){
        reproductionBehavior.birth(oceanSpace, currentPosition);
        noBirthTimeTicks=0;
    }

    public void resetStarvationTimeTicks(){
        starvationTimeTicks=0;
    }

    public int getCurrentStarvationTimeTicks() {
        return starvationTimeTicks;
    }

    public void eatIfNeed(){
        this.eatingOceanFishStrategy.eatIfNeed(this,oceanSpace);
    }

    public MoveToTargetStrategy getMoveToTargetStrategy() {
        return moveToTargetStrategy;
    }

    public OceanFishState getOceanFishState() {
        return this.oceanFishState;
    }
}

package model.fish.implementation;

import model.fish.implementation.target.Target;
import model.fish.implementation.target.TargetPriority;
import model.fish.interfaces.OceanFishState;
import model.parameters.FishState;
import model.parameters.Vector;

/**
 * Class for Ocean fish state, encapsulate moving behavior,
 * fish calculates next targets and move in ocean space
 */
public class MovingOceanFishState implements OceanFishState{

    private OceanFish oceanFish;
    private Target currentTarget;

    private int restTimeTicks = 0;

    public MovingOceanFishState(OceanFish oceanFish) {
        this.oceanFish = oceanFish;
    }

    @Override
    public FishState getState() {
        return FishState.MOVING;
    }

    @Override
    public void action() {
        oceanFish.eatIfNeed();

        calculateTargetIfNeed();
        moveToTarget();
    }

    private void calculateTargetIfNeed() {
        if (currentTarget == null || isReachedTarget() || currentTarget.getPriority()== TargetPriority.LOW)
            currentTarget = oceanFish.calculateTargetPosition();
    }

    private void moveToTarget() {
            this.oceanFish.moveToTarget(currentTarget);
    }

    private boolean isReachedTarget(){
        return currentTarget.getPosition().equals(oceanFish.getCurrentPosition());
    }

    public Target getCurrentTarget() {
        return currentTarget;
    }

    public int getRestTimeTicks() {
        return restTimeTicks;
    }

}

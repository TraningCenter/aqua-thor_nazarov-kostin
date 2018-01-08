package model.fish.implementation;

import model.fish.implementation.target.Target;
import model.fish.implementation.target.TargetPriority;
import model.fish.interfaces.OceanFishState;
import model.parameters.Vector;

public class MovingOceanFishState implements OceanFishState{

    private OceanFish oceanFish;
    private Target currentTarget;

    private int restTimeTicks = 0;

    public MovingOceanFishState(OceanFish oceanFish) {
        this.oceanFish = oceanFish;
    }

    @Override
    public void action() {
        calculateTargetIfNeed();
        moveToTarget();
    }

    public void setCurrentTarget(Target currentTarget) {
        this.currentTarget = currentTarget;
    }

    private void calculateTargetIfNeed() {
        if (currentTarget == null || isReachedTarget() || currentTarget.getPriority()== TargetPriority.LOW)
            currentTarget = oceanFish.calculateTargetPosition();
    }

    private void moveToTarget() {
        if (isRestedToMove(currentTarget.getPosition()))
        {
            this.oceanFish.moveToTarget(currentTarget);
            resetRestTime();
        }
        else
            rest();
    }

    private boolean isRestedToMove(Vector position) {

        return restTimeTicks>=oceanFish.getTimeToMoveToPosition(position);
    }

    private void rest() {
        restTimeTicks++;
    }

    private boolean isReachedTarget(){
        return currentTarget.getPosition().equals(oceanFish.getCurrentPosition());
    }

    private void resetRestTime() {
        this.restTimeTicks = 0;
    }

    public Target getCurrentTarget() {
        return currentTarget;
    }

    public int getRestTimeTicks() {
        return restTimeTicks;
    }
}

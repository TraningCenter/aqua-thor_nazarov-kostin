package model.fish.implementation;

import model.fish.interfaces.OceanFishState;
import model.parameters.Vector;

public class MovingOceanFishState implements OceanFishState{

    private OceanFish oceanFish;
    private Target currentTarget;

    public MovingOceanFishState(OceanFish oceanFish) {
        this.oceanFish = oceanFish;
    }

    @Override
    public void action() {
        calculateTargetIfNeed();
        moveToTarget();
    }

    private void calculateTargetIfNeed() {
        if (currentTarget == null || currentTarget.getPriority()== TargetPriority.LOW)
            currentTarget = oceanFish.calculateTargetPosition();
    }

    private void moveToTarget() {
        this.oceanFish.moveToTarget(currentTarget);
    }

    public Target getCurrentTarget() {
        return currentTarget;
    }

    public void setCurrentTarget(Target currentTarget) {
        this.currentTarget = currentTarget;
    }
}

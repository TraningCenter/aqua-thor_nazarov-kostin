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
        Vector direction = new Vector();

        int diffX = oceanFish.getCurrentPosition().getX() - currentTarget.getPosition().getX();
        int diffY = oceanFish.getCurrentPosition().getY() - currentTarget.getPosition().getY();

        //TODO: moving in borderless
    }
}

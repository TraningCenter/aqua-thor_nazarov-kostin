package model.fish.implementation;

import model.fish.interfaces.MoveToTargetStrategy;
import model.ocean.interfaces.OceanSpace;
import model.parameters.Vector;

public class BorderedMoveToTargetStrategy implements MoveToTargetStrategy {
    @Override
    public void moveToTarget(OceanFish oceanFish, OceanSpace oceanSpace, Target target) {
        Integer xDirection = oceanFish.getCurrentPosition().getX()>target.getPosition().getX()?-1
                :oceanFish.getCurrentPosition().getX()<target.getPosition().getX()?-1
                :0;
        Integer yDirection = oceanFish.getCurrentPosition().getY()>target.getPosition().getY()?-1
                :oceanFish.getCurrentPosition().getY()<target.getPosition().getY()?-1
                :0;

        oceanFish.move(new Vector(xDirection,yDirection));
    }
}

package model.fish.implementation;

import model.fish.implementation.target.Target;
import model.fish.interfaces.MoveToTargetStrategy;
import model.ocean.interfaces.OceanSpace;
import model.parameters.Vector;


/**
 * Class for moving to target in bordered ocean, cant go out of border
 */
public class BorderedMoveToTargetStrategy implements MoveToTargetStrategy {
    @Override
    public void moveToTarget(OceanFish oceanFish, OceanSpace oceanSpace, Target target) {
        Integer xDirection = oceanFish.getCurrentPosition().getX()>target.getPosition().getX()?-1
                :oceanFish.getCurrentPosition().getX()<target.getPosition().getX()?1
                :0;

        Integer yDirection = oceanFish.getCurrentPosition().getY()>target.getPosition().getY()?-1
                :oceanFish.getCurrentPosition().getY()<target.getPosition().getY()?1
                :0;

        if (xDirection!=0 && yDirection!=0) {
            if (Math.random() > 0.5)
                xDirection = 0;
            else yDirection = 0;
        }

        oceanFish.move(new Vector(xDirection,yDirection));
    }
}

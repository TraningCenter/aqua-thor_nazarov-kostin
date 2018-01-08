package model.fish.implementation;

import model.fish.implementation.target.Target;
import model.fish.interfaces.MoveToTargetStrategy;
import model.ocean.interfaces.OceanSpace;
import model.parameters.Vector;

public class BorderlessMoveToTargetStrategy implements MoveToTargetStrategy {
    @Override
    public void moveToTarget(OceanFish oceanFish, OceanSpace oceanSpace, Target target) {
        int xDirection = oceanFish.getCurrentPosition().getX()>target.getPosition().getX()?-1
                :oceanFish.getCurrentPosition().getX()<target.getPosition().getX()?1
                :0;

        int yDirection = oceanFish.getCurrentPosition().getY()>target.getPosition().getY()?-1
                :oceanFish.getCurrentPosition().getY()<target.getPosition().getY()?1
                :0;

        int sizeX = oceanSpace.getOceanSize().getX();
        int sizeY = oceanSpace.getOceanSize().getY();
        int doubleDistanceX =2*Math.abs(target.getPosition().getX() - oceanFish.getCurrentPosition().getX());
        int doubleDistanceY =2*Math.abs(target.getPosition().getY() - oceanFish.getCurrentPosition().getY());

        if (sizeX < doubleDistanceX || (sizeX == doubleDistanceX && Math.random() > 0.5))
            xDirection=-xDirection;

        if (sizeY < doubleDistanceY || (sizeY == doubleDistanceY && Math.random() > 0.5))
            yDirection=-yDirection;

        if (xDirection!=0 && yDirection!=0) {
            if (Math.random() > 0.5)
                xDirection = 0;
            else yDirection = 0;
        }

        oceanFish.move(new Vector(xDirection,yDirection));
    }
}

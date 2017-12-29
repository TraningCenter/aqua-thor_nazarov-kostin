package model.fish;

import model.fish.implementation.*;
import model.fish.interfaces.OceanFishState;
import model.fish.interfaces.ReproductionBehavior;
import model.fish.interfaces.TargetCalculationFishStrategy;
import model.ocean.interfaces.OceanSpace;
import model.parameters.Vector;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class OceanFishTests {

    //TODO:?????
    @Test
    public void canMoveToTargetInBorderedSpace(){
        //Array
        ReproductionBehavior mockReproductionBehavior = mock(ReproductionBehavior.class);
        BorderedMoveToTargetStrategy borderedMoveToTargetStrategy = new BorderedMoveToTargetStrategy();
        TargetCalculationFishStrategy targetCalculationFishStrategy = mock(TargetCalculationFishStrategy.class);
        OceanSpace mockOceanSpace = mock(OceanSpace.class);

        Vector startPosition = new Vector(0,0);
        Target currentTarget = new Target(new Vector(2,2), TargetPriority.HIGH);

        OceanFish oceanFish = Util.createOceanFishInMocks(startPosition,currentTarget);

        //Act
        for (int i=0;i<10;i++)
            oceanFish.action();

        //Assert
        Assert.assertEquals(Integer.valueOf(2), oceanFish.getCurrentPosition().getX());
        Assert.assertEquals(Integer.valueOf(2), oceanFish.getCurrentPosition().getY());
    }
}

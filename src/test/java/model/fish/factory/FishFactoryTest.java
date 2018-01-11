package model.fish.factory;

import model.fish.implementation.*;
import model.ocean.OceanSpaceMock;
import model.ocean.implementaion.DefaultOcean;
import model.ocean.interfaces.OceanSpace;
import model.parameters.FishParameters;
import model.parameters.Vector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parameters.OceanParametersMock;

public class FishFactoryTest {

    private FishFactory borderedFactory;
    private FishFactory borderlessfactory;
    private FishParameters fishParameters;
    private Vector startPosition;
    private OceanSpace oceanSpace;

    @Before
    public void init(){
        borderedFactory = new BorderedFishFactory();
        borderlessfactory = new BorderlessFishFactory();
        fishParameters = new FishParameters(10,7,5,4,6);
        startPosition = new Vector(3,2);
        oceanSpace  = new OceanSpaceMock();
    }

    @Test
    public void borderedFactoryPassiveTest(){
      OceanFish fish = (OceanFish) borderedFactory.createFish(FishType.PASSIVE,fishParameters,startPosition,oceanSpace);
      testFish(fish);
      Assert.assertEquals(FishType.PASSIVE,fish.getType());
      Assert.assertEquals(BorderedMoveToTargetStrategy.class, fish.getMoveToTargetStrategy().getClass());

    }

    @Test
    public void borderedFactoryAgressiveTest(){
        OceanFish fish = (OceanFish) borderedFactory.createFish(FishType.AGGRESSIVE,fishParameters,startPosition,oceanSpace);
        testFish(fish);
        Assert.assertEquals(FishType.AGGRESSIVE,fish.getType());
        Assert.assertEquals(BorderedMoveToTargetStrategy.class, fish.getMoveToTargetStrategy().getClass());

    }

    @Test
    public void borderlessFactoryPassiveTest(){
        OceanFish fish = (OceanFish) borderlessfactory.createFish(FishType.PASSIVE,fishParameters,startPosition,oceanSpace);
        testFish(fish);
        Assert.assertEquals(FishType.PASSIVE,fish.getType());
        Assert.assertEquals(BorderlessMoveToTargetStrategy.class, fish.getMoveToTargetStrategy().getClass());

    }

    @Test
    public void borderlessFactoryAgressiveTest(){
        OceanFish fish = (OceanFish) borderlessfactory.createFish(FishType.AGGRESSIVE,fishParameters,startPosition,oceanSpace);
        testFish(fish);
        Assert.assertEquals(FishType.AGGRESSIVE,fish.getType());
        Assert.assertEquals(BorderlessMoveToTargetStrategy.class, fish.getMoveToTargetStrategy().getClass());

    }

    private void testFish(OceanFish fish){
        FishParameters lifeParameters = fish.getLifeParameters();
        Assert.assertEquals((Integer)10, lifeParameters.getReproductionPeriodTicks());
        Assert.assertEquals((Integer)7, lifeParameters.getLifeTimeTicks());
        Assert.assertEquals((Integer)5, lifeParameters.getStarvationTimeTicks());
        Assert.assertEquals((Integer)4, lifeParameters.getSmellSenseDistance());
        Assert.assertEquals((Integer)6, lifeParameters.getTimeToMoveThroughOneCell());
        Assert.assertEquals((Integer)3, fish.getCurrentPosition().getX());
        Assert.assertEquals((Integer)2, fish.getCurrentPosition().getY());
        Assert.assertEquals(BirthOceanFishState.class,fish.getOceanFishState().getClass());
    }

}

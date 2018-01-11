package configurator;

import configurator.implementation.DefaultFishesCreator;
import configurator.interfaces.FishesCreator;
import model.fish.interfaces.Fish;
import model.ocean.OceanSpaceMock;
import model.parameters.OceanParameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parameters.OceanParametersMock;

import java.util.List;

public class FishesCreatorTest {

    private FishesCreator creator;

    @Before
    public void init(){
        creator = new DefaultFishesCreator();
    }

    @Test
    public void creationTest(){
       List<Fish> fishes = creator.createFishes(new OceanParametersMock().getParameters(),new OceanSpaceMock());
        Assert.assertEquals(25,fishes.size());
    }
}

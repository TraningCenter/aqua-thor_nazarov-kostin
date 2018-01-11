package configurator;

import configurator.implementation.XmlOceanCreator;
import configurator.interfaces.OceanCreator;
import model.ocean.implementaion.BorderedCellBehavior;
import model.ocean.implementaion.DefaultOcean;
import model.ocean.interfaces.Ocean;
import model.parameters.Flow;
import model.parameters.Rectangle;
import model.parameters.Vector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import readers.XmlParamsReaderMock;

import java.util.ArrayList;
import java.util.List;

public class OceanCreatorTest {
    private OceanCreator creator;
    private List<Flow> flows;

    @Before
    public void init(){
        creator = new XmlOceanCreator();
        Flow flow1 = new Flow();
        flow1.setRectangle(new Rectangle(10, 30, 50, 20));
        flow1.setDirection(new Vector(4, 5));
        flow1.setStrength(3);
        Flow flow2 = new Flow();
        flow2.setRectangle(new Rectangle(20, 5, 30, 40));
        flow2.setDirection(new Vector(5, 9));
        flow2.setStrength(1);
        flows = new ArrayList<>();
        flows.add(flow1);
        flows.add(flow2);
    }

    @Test
    public void testOceanCreation(){
       DefaultOcean ocean =  (DefaultOcean)creator.createOcean(null, new XmlParamsReaderMock());
       Assert.assertEquals(25, ocean.getFishes().size());
       Assert.assertEquals(flows.get(0),ocean.getFlows().get(0));
       Assert.assertEquals(flows.get(1),ocean.getFlows().get(1));
       Assert.assertEquals(new Vector(100, 100),ocean.getOceanSize());
       Assert.assertEquals(BorderedCellBehavior.class,ocean.getCellsBehavior().getClass());
    }
}

package model.ocean;

import model.grid.interfaces.CellGrid;
import model.ocean.implementaion.DefaultOcean;
import model.ocean.interfaces.CellsBehavior;
import model.parameters.Flow;
import model.parameters.Rectangle;
import model.parameters.Vector;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class DefaultOceanTests {

    @Test
    public void canGetFlowStrength(){
        //Array
        Vector baseDirection = new Vector(10,0);
        Vector basePosition = new Vector(5,5);

        List<Flow> flows = new LinkedList<>();
        // -> 5
        flows.add(new Flow(new Vector(1,0), 5, new Rectangle(0,0,10,10))); //5,0
        // <- 5
        flows.add(new Flow(new Vector(-1,0), 3, new Rectangle(0,0,10,10)));//2,0
        // | 5
        // v 2
        flows.add(new Flow(new Vector(0,-1), 2, new Rectangle(0,0,10,10)));//2,-2
        // outside
        flows.add(new Flow(new Vector(-1,0), 10, new Rectangle(10,10,10,10)));

        DefaultOcean defaultOcean = new DefaultOcean(mock(CellsBehavior.class), flows, mock(CellGrid.class));

        //Act
        Integer strength = defaultOcean.getFlowStrength(basePosition, baseDirection);

        //Assert
        Assert.assertEquals(Integer.valueOf(2), strength);
    }


}

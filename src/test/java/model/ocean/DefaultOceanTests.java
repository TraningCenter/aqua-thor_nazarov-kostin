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
    public void canCalculateFlowStrength(){
        //Array
        Vector baseDirection = new Vector(0,1);
        Vector basePosition = new Vector(5,5);

        List<Flow> flows = new LinkedList<>();
        flows.add(new Flow(new Vector(0,1), 5, new Rectangle(0,0,10,10)));

        DefaultOcean defaultOcean = new DefaultOcean(mock(CellsBehavior.class), flows, mock(CellGrid.class));

        //Act
        Integer strength = defaultOcean.getFlowStrength(basePosition, baseDirection);

        //Assert
        Assert.assertEquals(Integer.valueOf(5), strength);
    }




}

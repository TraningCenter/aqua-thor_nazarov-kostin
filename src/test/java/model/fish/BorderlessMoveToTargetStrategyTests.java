package model.fish;

import model.cell.implementation.DefaultCell;
import model.cell.interfaces.Cell;
import model.fish.implementation.BorderlessMoveToTargetStrategy;
import model.fish.implementation.OceanFish;
import model.fish.implementation.target.Target;
import model.fish.implementation.target.TargetPriority;
import model.grid.implementation.DefaultCellGrid;
import model.ocean.implementaion.BorderedCellBehavior;
import model.ocean.implementaion.DefaultOcean;
import model.parameters.Vector;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

public class BorderlessMoveToTargetStrategyTests {

    @Test
    public void canMoveThroughBorder(){
        //Array
        /*
        1 - current
        2 - target
        0)
        000
        102
        000
        1)
        000
        201
        000
        2)
        010
        000
        020
        3)
        020
        000
        010
         */
        List<Vector> currentPositions = new LinkedList<>();
        List<Vector> targetPositions = new LinkedList<>();
        List<Vector> expectedDirections = new LinkedList<>();

        currentPositions.add(new Vector(0,1));
        targetPositions.add(new Vector(2,1));
        expectedDirections.add(new Vector(-1,0));

        currentPositions.add(new Vector(2,1));
        targetPositions.add(new Vector(0,1));
        expectedDirections.add(new Vector(1,0));

        currentPositions.add(new Vector(1,0));
        targetPositions.add(new Vector(1,2));
        expectedDirections.add(new Vector(0,-1));

        currentPositions.add(new Vector(1,2));
        targetPositions.add(new Vector(1,0));
        expectedDirections.add(new Vector(0,1));

        BorderlessMoveToTargetStrategy moveToTargetStrategy = new BorderlessMoveToTargetStrategy();

        int size = 3;
        Cell[][] cells = new Cell[size][size];

        for (int i=0;i<size;i++)
            for (int j=0;j<size;j++){
                cells[i][j]=new DefaultCell(new Vector(i, j));
            }

        DefaultCellGrid cellGrid = new DefaultCellGrid(cells);
        DefaultOcean oceanSpace = new DefaultOcean(new BorderedCellBehavior(), new LinkedList<>(), cellGrid);

        for (int i=0;i<currentPositions.size();i++){
            OceanFish mockOceanFish = mock(OceanFish.class);
            when(mockOceanFish.getCurrentPosition()).thenReturn(currentPositions.get(i));

            //Act
            moveToTargetStrategy.moveToTarget(mockOceanFish, oceanSpace, new Target(targetPositions.get(i), TargetPriority.HIGH));

            //Assert
            ArgumentCaptor<Vector> argument = ArgumentCaptor.forClass(Vector.class);
            verify(mockOceanFish).move(argument.capture());

            Assert.assertEquals(expectedDirections.get(i), argument.getValue());
        }


    }
}

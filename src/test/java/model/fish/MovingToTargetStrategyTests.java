package model.fish;

import model.cell.implementation.DefaultCell;
import model.cell.interfaces.Cell;
import model.fish.implementation.BorderedMoveToTargetStrategy;
import model.fish.implementation.OceanFish;
import model.fish.implementation.Target;
import model.fish.implementation.TargetPriority;
import model.grid.implementation.DefaultCellGrid;
import model.grid.interfaces.CellGrid;
import model.ocean.implementaion.BorderlessCellBehavior;
import model.ocean.implementaion.DefaultOcean;
import model.ocean.interfaces.OceanSpace;
import model.parameters.Vector;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

import static org.mockito.Mockito.mock;

public class MovingToTargetStrategyTests  {

    //TODO:?????
    @Test
    public void canMoveWithBorderedStrategy() {
        //Array
        Integer currentX= 0;
        Integer currentY= 0;

        BorderedMoveToTargetStrategy borderedMoveToTargetStrategy = new BorderedMoveToTargetStrategy();
        OceanFish oceanFish = mock(OceanFish.class);

        Cell[][] cells = new Cell[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                cells[i][j] = new DefaultCell(new Vector(i, j));

        CellGrid cellGrid = new DefaultCellGrid(cells);
        OceanSpace oceanSpace = new DefaultOcean(new BorderlessCellBehavior(), new LinkedList<>(), cellGrid);

        Target target = new Target(new Vector(2,2), TargetPriority.HIGH);
        //Act
        borderedMoveToTargetStrategy.moveToTarget(oceanFish, oceanSpace, target);
        borderedMoveToTargetStrategy.moveToTarget(oceanFish, oceanSpace, target);
        borderedMoveToTargetStrategy.moveToTarget(oceanFish, oceanSpace, target);
        borderedMoveToTargetStrategy.moveToTarget(oceanFish, oceanSpace, target);

        //Assert
        Assert.assertEquals(target.getPosition().getX(), oceanFish.getCurrentPosition().getX());
        Assert.assertEquals(target.getPosition().getY(), oceanFish.getCurrentPosition().getY());

    }
}

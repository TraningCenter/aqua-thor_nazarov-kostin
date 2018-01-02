package model.fish;

import model.cell.implementation.DefaultCell;
import model.cell.interfaces.Cell;
import model.fish.implementation.*;
import model.fish.interfaces.*;
import model.grid.implementation.DefaultCellGrid;
import model.grid.interfaces.CellGrid;
import model.ocean.implementaion.BorderedCellBehavior;
import model.ocean.implementaion.BorderlessCellBehavior;
import model.ocean.implementaion.DefaultOcean;
import model.ocean.interfaces.OceanSpace;
import model.parameters.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OceanFishTests {

    FishType fishType;
    FishParameters fishParameters;
    Vector startPosition;
    List<Flow> flows;
    Integer size = 5;
    Cell[][] cells;
    CellGrid cellGrid;
    OceanSpace oceanSpace;

    OceanFish fish;

    @Before
    public void init(){
        fishType = FishType.PASSIVE;
        fishParameters = new FishParameters(50,200,20,3,3);
        startPosition = new Vector(1,1);
        flows = new LinkedList<Flow>(){{add(new Flow(Directions.RIGHT,1,new Rectangle(0,0,10,10)));}};

        size = 5;
        cells = new Cell[size][size];

        for (int i=0;i<size;i++)
            for (int j=0;j<size;j++){
                cells[i][j]=new DefaultCell(new Vector(i, j));
            }

        cellGrid = new DefaultCellGrid(cells);
        oceanSpace = new DefaultOcean(new BorderedCellBehavior(), flows, cellGrid);

        TargetCalculationFishStrategy targetCalculationFishStrategy = new BorderedTargetCalculationFishStrategy();
        MoveToTargetStrategy moveToTargetStrategy = new BorderedMoveToTargetStrategy();
        TargetCellPredicate targetCellPredicate = cell -> true;
        TargetPriorityCalcFunction targetPriorityCalcFunction = relativeCell -> TargetPriority.HIGH;

        fish = new OceanFish(fishType, fishParameters, startPosition, oceanSpace, new DoingNothingOceanFishState(),
                targetCalculationFishStrategy, mock(ReproductionBehavior.class), moveToTargetStrategy, targetCellPredicate,targetPriorityCalcFunction);
    }

    @Test
    public void canCalcTarget(){
        //Array

        //Act
        Target target = fish.calculateTargetPosition();

        //Assert
        Assert.assertEquals(TargetPriority.HIGH, target.getPriority());
        Assert.assertEquals(Integer.valueOf(0), target.getPosition().getX());
        Assert.assertEquals(Integer.valueOf(0), target.getPosition().getY());

    }

    @Test
    public void canMoveWithBorderedStrategy() {
        //Array
        Integer currentX= 0;
        Integer currentY= 0;

        BorderedMoveToTargetStrategy borderedMoveToTargetStrategy = new BorderedMoveToTargetStrategy();

        Cell[][] cells = new Cell[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                cells[i][j] = new DefaultCell(new Vector(i, j));

        CellGrid cellGrid = new DefaultCellGrid(cells);
        OceanSpace oceanSpace = new DefaultOcean(new BorderedCellBehavior(), new LinkedList<>(), cellGrid);

        Target target = new Target(new Vector(2,2), TargetPriority.HIGH);
        //Act
        borderedMoveToTargetStrategy.moveToTarget(fish, oceanSpace, target);
        borderedMoveToTargetStrategy.moveToTarget(fish, oceanSpace, target);
        borderedMoveToTargetStrategy.moveToTarget(fish, oceanSpace, target);
        borderedMoveToTargetStrategy.moveToTarget(fish, oceanSpace, target);

        //Assert
        Assert.assertEquals(target.getPosition().getX(), fish.getCurrentPosition().getX());
        Assert.assertEquals(target.getPosition().getY(), fish.getCurrentPosition().getY());
    }

    @Test
    public void canRandomlyMoveWithBorderedStrategy(){
        //Array

        //Act
        fish.action();



        //Assert

    }
}

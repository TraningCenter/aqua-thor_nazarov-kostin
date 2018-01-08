package model.fish;

import model.cell.implementation.DefaultCell;
import model.cell.interfaces.Cell;
import model.cell.interfaces.RelativeCell;
import model.fish.implementation.*;
import model.fish.implementation.target.RandomTargetCalculationFishStrategy;
import model.fish.implementation.target.Target;
import model.fish.implementation.target.TargetPriority;
import model.fish.interfaces.*;
import model.fish.interfaces.target.TargetCalculationFishStrategy;
import model.fish.interfaces.target.TargetCellPredicate;
import model.fish.interfaces.target.TargetPriorityCalcFunction;
import model.grid.implementation.DefaultCellGrid;
import model.grid.interfaces.CellGrid;
import model.ocean.implementaion.BorderedCellBehavior;
import model.ocean.implementaion.DefaultOcean;
import model.ocean.interfaces.OceanSpace;
import model.parameters.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.text.Position;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;

public class OceanFishBorderedTests {

    FishType fishType;
    FishParameters fishParameters;
    Vector startPosition;
    List<Flow> flows;
    Integer size = 5;
    Cell[][] cells;
    CellGrid cellGrid;
    OceanSpace oceanSpace;

    OceanFish fish;
    private TargetCellPredicate targetCellPredicate;

    @Before
    public void init(){
        fishType = FishType.PASSIVE;
        fishParameters = new FishParameters(50,200,20,2,3);
        startPosition = new Vector(1,1);
       // flows = new LinkedList<Flow>(){{add(new Flow(Directions.RIGHT,1,new Rectangle(0,0,10,10)));}};
        flows = new LinkedList<>();

        size = 5;
        cells = new Cell[size][size];

        for (int i=0;i<size;i++)
            for (int j=0;j<size;j++){
                cells[i][j]=new DefaultCell(new Vector(i, j));
            }

        cellGrid = new DefaultCellGrid(cells);
        oceanSpace = new DefaultOcean(new BorderedCellBehavior(), flows, cellGrid);

        TargetCalculationFishStrategy targetCalculationFishStrategy = new RandomTargetCalculationFishStrategy();
        MoveToTargetStrategy moveToTargetStrategy = new BorderedMoveToTargetStrategy();
        targetCellPredicate = cell -> true;
        TargetPriorityCalcFunction targetPriorityCalcFunction = relativeCell -> TargetPriority.HIGH;

        fish = new OceanFish(fishType, fishParameters, startPosition, oceanSpace, new DoingNothingOceanFishState(),
                targetCalculationFishStrategy, mock(ReproductionBehavior.class), moveToTargetStrategy, targetCellPredicate,targetPriorityCalcFunction);
    }

    @Test
    public void canCalcTarget(){
        //Array
        List<RelativeCell> listOfRelativeCells = oceanSpace.getCellsInRange(fish.getCurrentPosition(), fishParameters.getSmellSenseDistance())
                .stream()
                .filter(relativeCell -> targetCellPredicate.test(relativeCell) && !relativeCell.getRelativePosition().equals(Vector.Zero()))
                .collect(Collectors.toList());

        //Act
        Target target = fish.calculateTargetPosition();

        //Assert
        Assert.assertEquals(TargetPriority.HIGH, target.getPriority());
        Assert.assertTrue(listOfRelativeCells.stream().filter(relativeCell -> relativeCell.getPosition().equals(target.getPosition())).count()==1);

    }

    @Test
    public void canMove() {
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
    public void canRandomlyMove(){
        //Array
        MovingOceanFishState movingOceanFishState = new MovingOceanFishState(fish);
        fish.changeState(movingOceanFishState);


        //Act && Assert
        Target lastTarget =movingOceanFishState.getCurrentTarget();
        Vector lastPosition = fish.getCurrentPosition();
        int currentRestTimeTicks = 0;

        Assert.assertNull(lastTarget);
        for (int i =0;i<30;i++) {
            lastPosition=new Vector(fish.getCurrentPosition().getX(), fish.getCurrentPosition().getY());
            if (movingOceanFishState.getCurrentTarget()!=null)
                lastTarget =new Target(movingOceanFishState.getCurrentTarget().getPosition(),movingOceanFishState.getCurrentTarget().getPriority());

            fish.action();
            Assert.assertNotNull(movingOceanFishState.getCurrentTarget());
            currentRestTimeTicks++;

            //Assert moving
            if (currentRestTimeTicks-1 >= fishParameters.getTimeToMoveThroughOneCell()) {
                Assert.assertNotEquals(lastPosition, fish.getCurrentPosition());
                currentRestTimeTicks=0;
            }

            //Assert target changing
            if (lastTarget!=null && lastPosition.equals(lastTarget.getPosition()))
                Assert.assertNotEquals(lastTarget, movingOceanFishState.getCurrentTarget());
        }
    }
}

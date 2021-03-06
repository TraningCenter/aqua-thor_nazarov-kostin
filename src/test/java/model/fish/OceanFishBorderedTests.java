package model.fish;

import model.cell.implementation.DefaultCell;
import model.cell.interfaces.Cell;
import model.cell.interfaces.RelativeCell;
import model.fish.implementation.*;
import model.fish.implementation.target.EscapeTargetCalculationFishStrategy;
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
    DefaultOcean defaultOcean;

    OceanFish fish;
    private TargetCellPredicate targetCellPredicate;
    private BorderedMoveToTargetStrategy moveToTargetStrategy;
    private TargetPriorityCalcFunction targetPriorityCalcFunction;
    private TargetCalculationFishStrategy targetCalculationFishStrategy;

    @Before
    public void init(){
        fishType = FishType.PASSIVE;
        fishParameters = new FishParameters(20000,20000,20000,2,2);
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
        defaultOcean = new DefaultOcean(new BorderedCellBehavior(), flows, cellGrid);

        targetCalculationFishStrategy = new EscapeTargetCalculationFishStrategy();
        moveToTargetStrategy = new BorderedMoveToTargetStrategy();
        targetCellPredicate = cell -> true;
        targetPriorityCalcFunction = relativeCell -> TargetPriority.HIGH;

        fish = new OceanFish(fishType, fishParameters, startPosition, defaultOcean, new DoingNothingOceanFishState(),
                targetCalculationFishStrategy, mock(ReproductionBehavior.class), moveToTargetStrategy, targetCellPredicate,targetPriorityCalcFunction, new PassiveEatingOceanFishStrategy());

        defaultOcean.addFish(fish);
    }

    @Test
    public void canCalcTarget(){
        //Array
        List<RelativeCell> listOfRelativeCells = defaultOcean.getCellsInRange(fish.getCurrentPosition(), fishParameters.getSmellSenseDistance())
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

        Target target = new Target(new Vector(2,2), TargetPriority.HIGH);
        MovingOceanFishState movingOceanFishState = new MovingOceanFishState(fish);
        movingOceanFishState.setCurrentTarget(target);
        fish.changeState(movingOceanFishState);

        Cell[][] cells = new Cell[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                cells[i][j] = new DefaultCell(new Vector(i, j));

        CellGrid cellGrid = new DefaultCellGrid(cells);
        OceanSpace oceanSpace = new DefaultOcean(new BorderedCellBehavior(), new LinkedList<>(), cellGrid);

        //Act
        fish.action();
        fish.action();
        fish.action();
        fish.action();
        fish.action();
        fish.action();

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
        int tickNum=0;

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

    @Test
    public void fishesAreDyingByAge(){
        //Array
        fishParameters.setLifeTimeTicks(10);

        //Act
        for (int i=0;i<9;i++){
            defaultOcean.addFish(new OceanFish(fishType, fishParameters, startPosition, defaultOcean, new DoingNothingOceanFishState(),
                            targetCalculationFishStrategy, mock(ReproductionBehavior.class), moveToTargetStrategy, targetCellPredicate,targetPriorityCalcFunction, new PassiveEatingOceanFishStrategy()));
            System.out.println(defaultOcean.getFishes().size());
        }

        for (int i=0;i<9;i++){
            defaultOcean.update();
            System.out.println(defaultOcean.getFishes().size());
        }

        Assert.assertEquals(10, defaultOcean.getFishes().size());

        for (int i=0;i<20;i++){
            defaultOcean.update();
            System.out.println(defaultOcean.getFishes().size());
        }

        Assert.assertEquals(0, defaultOcean.getFishes().size());
    }

}

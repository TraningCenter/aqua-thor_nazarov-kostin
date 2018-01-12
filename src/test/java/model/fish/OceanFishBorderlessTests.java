package model.fish;

import model.cell.implementation.DefaultCell;
import model.cell.interfaces.Cell;
import model.fish.implementation.*;
import model.fish.implementation.target.EscapeTargetCalculationFishStrategy;
import model.fish.implementation.target.Target;
import model.fish.implementation.target.TargetPriority;
import model.fish.interfaces.MoveToTargetStrategy;
import model.fish.interfaces.ReproductionBehavior;
import model.fish.interfaces.target.TargetCalculationFishStrategy;
import model.fish.interfaces.target.TargetCellPredicate;
import model.fish.interfaces.target.TargetPriorityCalcFunction;
import model.grid.implementation.DefaultCellGrid;
import model.grid.interfaces.CellGrid;
import model.ocean.implementaion.BorderlessCellBehavior;
import model.ocean.implementaion.DefaultOcean;
import model.ocean.interfaces.OceanSpace;
import model.parameters.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class OceanFishBorderlessTests {

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
    public void init() {
        fishType = FishType.PASSIVE;
        fishParameters = new FishParameters(50000, 20000, 20000, 2, 3);
        startPosition = new Vector(1, 1);
        // flows = new LinkedList<Flow>(){{add(new Flow(Directions.RIGHT,1,new Rectangle(0,0,10,10)));}};
        flows = new LinkedList<>();

        size = 5;
        cells = new Cell[size][size];

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                cells[i][j] = new DefaultCell(new Vector(i, j));
            }

        cellGrid = new DefaultCellGrid(cells);
        oceanSpace = new DefaultOcean(new BorderlessCellBehavior(), flows, cellGrid);

        TargetCalculationFishStrategy targetCalculationFishStrategy = new EscapeTargetCalculationFishStrategy();
        MoveToTargetStrategy moveToTargetStrategy = new BorderlessMoveToTargetStrategy();
        targetCellPredicate = cell -> true;
        TargetPriorityCalcFunction targetPriorityCalcFunction = relativeCell -> TargetPriority.HIGH;

        fish = new OceanFish(fishType, fishParameters, startPosition, oceanSpace, new DoingNothingOceanFishState(),
                targetCalculationFishStrategy, mock(ReproductionBehavior.class), moveToTargetStrategy, targetCellPredicate, targetPriorityCalcFunction, new PassiveEatingOceanFishStrategy());
    }


    @Test
    public void canRandomlyMoveWithoutFlows() {
        //Array
        MovingOceanFishState movingOceanFishState = new MovingOceanFishState(fish);
        fish.changeState(movingOceanFishState);

        //Act && Assert
        Target lastTarget = movingOceanFishState.getCurrentTarget();
        Vector startPositionForMovingToTarget = fish.getCurrentPosition();
        Vector lastPosition = fish.getCurrentPosition();
        int currentRestTimeTicks = 0;
        int countOfMoves = 0;

        System.out.println("current position : " + fish.getCurrentPosition());
        System.out.println("current target : null");
        System.out.println("current rest : " + fish.getCurrentRestedTimeTicks());
        System.out.println("");

        //assert that count of moves = nearest path
        Assert.assertNull(lastTarget);
        for (int i = 0; i < 1000; i++) {
            lastPosition = new Vector(fish.getCurrentPosition().getX(), fish.getCurrentPosition().getY());
            if (movingOceanFishState.getCurrentTarget() != null)
                lastTarget = new Target(movingOceanFishState.getCurrentTarget().getPosition(), movingOceanFishState.getCurrentTarget().getPriority());

            //fish.action();
            fish.action();
            Assert.assertNotNull(movingOceanFishState.getCurrentTarget());
            currentRestTimeTicks++;

            System.out.println("current position : " + fish.getCurrentPosition());
            System.out.println("last position : " + lastPosition);
            System.out.println("current target : " + movingOceanFishState.getCurrentTarget().getPosition());
            System.out.println("last target : " + lastTarget);
            System.out.println("current rest : " + fish.getCurrentRestedTimeTicks());
            System.out.println("");

            //Assert moving
            if (currentRestTimeTicks - 1 >= fishParameters.getTimeToMoveThroughOneCell())
            {/*
                System.out.println("current position : " + fish.getCurrentPosition());
                System.out.println("current target : " + movingOceanFishState.getCurrentTarget().getPosition());
                System.out.println("current rest : " + fish.getCurrentRestedTimeTicks());
                System.out.println("");*/

                Assert.assertNotEquals(lastPosition, fish.getCurrentPosition());
                countOfMoves++;
                currentRestTimeTicks = 0;
            }

            //Assert target changing
            if (lastTarget != null && lastPosition.equals(lastTarget.getPosition())) {
                Assert.assertNotEquals(lastTarget, movingOceanFishState.getCurrentTarget());

                System.out.println("last target : " + lastTarget.getPosition()
                        + "  new target : " + movingOceanFishState.getCurrentTarget().getPosition()
                        + "  |"
                        + "  assert distance - target : " + lastTarget.getPosition()
                        + "  position : " + startPositionForMovingToTarget
                        + "  count of moves : " + countOfMoves
                        + "  distance method : " + getNearestPathMovesCount(lastTarget.getPosition(), startPositionForMovingToTarget));
                Assert.assertEquals(getNearestPathMovesCount(lastTarget.getPosition(), startPositionForMovingToTarget), countOfMoves);

                startPositionForMovingToTarget = fish.getCurrentPosition();
                countOfMoves = 0;
                lastTarget = movingOceanFishState.getCurrentTarget();
            }

        }
    }

    private int getNearestPathMovesCount(Vector from, Vector to) {
        return Math.min(Math.abs(to.getX()-from.getX()), size - Math.abs(to.getX()-from.getX())) +
                Math.min(Math.abs(to.getY()-from.getY()), size - Math.abs(to.getY()-from.getY()));
    }
}

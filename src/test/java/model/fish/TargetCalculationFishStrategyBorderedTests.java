package model.fish;

import com.sun.org.apache.xpath.internal.operations.Bool;
import model.cell.implementation.DefaultCell;
import model.cell.interfaces.Cell;
import model.fish.implementation.FishType;
import model.fish.implementation.target.*;
import model.fish.interfaces.Fish;
import model.grid.implementation.DefaultCellGrid;
import model.ocean.implementaion.BorderedCellBehavior;
import model.ocean.implementaion.BorderlessCellBehavior;
import model.ocean.implementaion.DefaultOcean;
import model.parameters.Flow;
import model.parameters.Vector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyByte;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TargetCalculationFishStrategyBorderedTests {

    private DefaultOcean oceanSpace;
    private Cell[][] cells;

    @Before
    public void init(){
        LinkedList<Flow> flows = new LinkedList<>();

        int size = 5;
        cells = new Cell[size][size];

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                cells[i][j] = new DefaultCell(new Vector(i, j));
            }

        DefaultCellGrid cellGrid = new DefaultCellGrid(cells);
        oceanSpace = new DefaultOcean(new BorderedCellBehavior(), flows, cellGrid);
    }

    @Test
    public void canCalculateEscapeStrategy(){
        //Array
        EscapeTargetCalculationFishStrategy targetCalculationFishStrategy =
                new EscapeTargetCalculationFishStrategy();

        Integer range = 2;
        List<Vector> passiveFishPositions = new LinkedList<>();
        List<Vector> aggressiveFishPositions = new LinkedList<>();
        List<Vector> expectedTarget = new LinkedList<>();
        List<Boolean> canSeeAggressiveFish = new LinkedList<>();

        passiveFishPositions.add(new Vector(1,1));
        aggressiveFishPositions.add(new Vector(0,0));
        expectedTarget.add(new Vector(2,2));
        canSeeAggressiveFish.add(true);

        passiveFishPositions.add(new Vector(1,1));
        aggressiveFishPositions.add(new Vector(1,0));
        expectedTarget.add(new Vector(1,2));
        canSeeAggressiveFish.add(true);

        passiveFishPositions.add(new Vector(1,1));
        aggressiveFishPositions.add(new Vector(2,2));
        expectedTarget.add(new Vector(0,0));
        canSeeAggressiveFish.add(true);

        passiveFishPositions.add(new Vector(0,0));
        aggressiveFishPositions.add(new Vector(4,4));
        expectedTarget.add(new Vector(4,4));
        canSeeAggressiveFish.add(false);

        passiveFishPositions.add(new Vector(1,0));
        aggressiveFishPositions.add(new Vector(1,4));
        expectedTarget.add(new Vector(1,4));
        canSeeAggressiveFish.add(false);

        passiveFishPositions.add(new Vector(1,1));
        aggressiveFishPositions.add(new Vector(4,4));
        expectedTarget.add(new Vector(4,4));
        canSeeAggressiveFish.add(false);

        for (int i =0 ;i< passiveFishPositions.size();i++){
            Fish mockAggressiveFish = mock(Fish.class);
            when(mockAggressiveFish.getType()).thenReturn(FishType.AGGRESSIVE);
            when(mockAggressiveFish.getCurrentPosition()).thenReturn(aggressiveFishPositions.get(i));
            cells[aggressiveFishPositions.get(i).getX()][aggressiveFishPositions.get(i).getY()].add(mockAggressiveFish);

            //Act
            Target target = targetCalculationFishStrategy.calculateTarget(passiveFishPositions.get(i), range,
                    oceanSpace, new PassiveTargetCellPredicate(), new PassiveTargetPriorityCalcFunction());

            //Assert
            if (canSeeAggressiveFish.get(i))
                assertEquals(expectedTarget.get(i), target.getPosition());
            else
                assertEquals(TargetPriority.LOW, target.getPriority());

            //clear fish
            cells[aggressiveFishPositions.get(i).getX()][aggressiveFishPositions.get(i).getY()] =
                    new DefaultCell(new Vector(aggressiveFishPositions.get(i).getX(),aggressiveFishPositions.get(i).getY()));
        }
    }

    @Test
    public void canCalculateHuntingStrategy(){
        //Array
        HuntingTargetCalculationFishStrategy huntingTargetCalculationFishStrategy =
                new HuntingTargetCalculationFishStrategy();

        Integer range = 2;
        List<Vector> passiveFishPositions = new LinkedList<>();
        List<Vector> aggressiveFishPositions = new LinkedList<>();
        List<Vector> expectedTarget = new LinkedList<>();
        List<Boolean> canSeePassiveFish = new LinkedList<>();

        passiveFishPositions.add(new Vector(1,1));
        aggressiveFishPositions.add(new Vector(0,0));
        expectedTarget.add(new Vector(1,1));
        canSeePassiveFish.add(true);

        passiveFishPositions.add(new Vector(1,1));
        aggressiveFishPositions.add(new Vector(1,0));
        expectedTarget.add(new Vector(1,1));
        canSeePassiveFish.add(true);

        passiveFishPositions.add(new Vector(1,1));
        aggressiveFishPositions.add(new Vector(2,2));
        expectedTarget.add(new Vector(1,1));
        canSeePassiveFish.add(true);

        passiveFishPositions.add(new Vector(0,0));
        aggressiveFishPositions.add(new Vector(1,1));
        expectedTarget.add(new Vector(0,0));
        canSeePassiveFish.add(true);

        passiveFishPositions.add(new Vector(4,4));
        aggressiveFishPositions.add(new Vector(4,0));
        expectedTarget.add(new Vector(4,4));
        canSeePassiveFish.add(false);

        passiveFishPositions.add(new Vector(1,1));
        aggressiveFishPositions.add(new Vector(4,4));
        expectedTarget.add(new Vector(1,1));
        canSeePassiveFish.add(false);

        for (int i =0 ;i< passiveFishPositions.size();i++){
            Fish mockPassiveFish = mock(Fish.class);
            when(mockPassiveFish.getType()).thenReturn(FishType.PASSIVE);
            when(mockPassiveFish.getCurrentPosition()).thenReturn(passiveFishPositions.get(i));
            cells[passiveFishPositions.get(i).getX()][passiveFishPositions.get(i).getY()].add(mockPassiveFish);

            //Act
            Target target = huntingTargetCalculationFishStrategy.calculateTarget(aggressiveFishPositions.get(i), range,
                    oceanSpace, new AggressiveTargetCellPredicate(), new AggressiveTargetPriorityCalcFunction());

            //Assert
            if (canSeePassiveFish.get(i))
                assertEquals(expectedTarget.get(i), target.getPosition());
            else
                assertEquals(TargetPriority.LOW, target.getPriority());

            //clear fish
            cells[passiveFishPositions.get(i).getX()][passiveFishPositions.get(i).getY()] =
                    new DefaultCell(new Vector(passiveFishPositions.get(i).getX(),passiveFishPositions.get(i).getY()));
        }
    }

    @Test
    public void canCalculateHuntingStrategyWithNearestPassiveFish(){
        //Array
        HuntingTargetCalculationFishStrategy huntingTargetCalculationFishStrategy =
                new HuntingTargetCalculationFishStrategy();

        Vector passiveFishPosition1 = new Vector(2,3);
        Vector passiveFishPosition2 = new Vector(1,4);
        Vector aggressiveFishPosition = new Vector(1,1);
        Integer range = 2;

        Fish mockPassiveFish1 = mock(Fish.class);
        when(mockPassiveFish1.getType()).thenReturn(FishType.PASSIVE);
        when(mockPassiveFish1.getCurrentPosition()).thenReturn(passiveFishPosition1);
        cells[passiveFishPosition1.getX()][passiveFishPosition1.getY()].add(mockPassiveFish1);

        Fish mockPassiveFish2 = mock(Fish.class);
        when(mockPassiveFish2.getType()).thenReturn(FishType.PASSIVE);
        when(mockPassiveFish2.getCurrentPosition()).thenReturn(passiveFishPosition2);
        cells[passiveFishPosition2.getX()][passiveFishPosition2.getY()].add(mockPassiveFish1);

        //Act
        Target target = huntingTargetCalculationFishStrategy.calculateTarget(aggressiveFishPosition, range,
                oceanSpace, new AggressiveTargetCellPredicate(), new AggressiveTargetPriorityCalcFunction());

        //Assert
        assertEquals(passiveFishPosition1, target.getPosition());
    }

}

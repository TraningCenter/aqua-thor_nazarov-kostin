package model.ocean;

import model.cell.implementation.DefaultCell;
import model.cell.interfaces.Cell;
import model.cell.interfaces.RelativeCell;
import model.fish.implementation.FishType;
import model.fish.implementation.OceanFish;
import model.fish.interfaces.Fish;
import model.grid.interfaces.CellGrid;
import model.ocean.implementaion.BorderedCellBehavior;
import model.ocean.implementaion.BorderlessCellBehavior;
import model.ocean.implementaion.DefaultOcean;
import model.ocean.interfaces.CellsBehavior;
import model.ocean.interfaces.Ocean;
import model.parameters.Flow;
import model.parameters.Rectangle;
import model.parameters.Vector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class CellBehaviorTests {

    private CellGrid mockCellGrid;
    private Vector positionCenter;
    private int range;

    @Before
    public void init(){

        mockCellGrid = mock(CellGrid.class);

        Integer size = 5;
        range = 2;
        Cell[][] cells = new Cell[size][size];

        for (int i=0;i<size;i++)
            for (int j=0;j<size;j++){
                Vector position = new Vector(i, j);
                DefaultCell defaultCell = new DefaultCell(position);

                cells[i][j]=defaultCell;
                when(mockCellGrid.getCell(eq(new Vector(i,j)))).thenReturn(defaultCell);
            }

        when(mockCellGrid.getSize()).thenReturn(new Vector(5,5));

        positionCenter = new Vector(0,0);
    }

    @Test
    public void canGiveCellsInRangeInBorderedOcean(){
        //Array
        CellsBehavior cellsBehavior = new BorderedCellBehavior();

        //Act
        List<RelativeCell> cellsInRange = cellsBehavior.getCellsInRange(mockCellGrid, positionCenter, range);

        /*
        ocean
         00 10 20 30 40
         01 11 21 31 41
         02 12 22 32 42
         03 13 23 33 43
         04 14 24 34 44

         in range
         00 10 20
         01 11 21
         02 12 22
         */

        //Assert
        assertEquals(cellsInRange.get(0).getRelativePosition(), new Vector(0,0));
        assertEquals(cellsInRange.get(1).getRelativePosition(), new Vector(1,0));
        assertEquals(cellsInRange.get(2).getRelativePosition(), new Vector(2,0));
        assertEquals(cellsInRange.get(3).getRelativePosition(), new Vector(0,1));
        assertEquals(cellsInRange.get(4).getRelativePosition(), new Vector(1,1));
        assertEquals(cellsInRange.get(5).getRelativePosition(), new Vector(2,1));
        assertEquals(cellsInRange.get(6).getRelativePosition(), new Vector(0,2));
        assertEquals(cellsInRange.get(7).getRelativePosition(), new Vector(1,2));
        assertEquals(cellsInRange.get(8).getRelativePosition(), new Vector(2,2));

        assertEquals(cellsInRange.get(0).getPosition(), new Vector(0,0));
        assertEquals(cellsInRange.get(1).getPosition(), new Vector(1,0));
        assertEquals(cellsInRange.get(2).getPosition(), new Vector(2,0));
        assertEquals(cellsInRange.get(3).getPosition(), new Vector(0,1));
        assertEquals(cellsInRange.get(4).getPosition(), new Vector(1,1));
        assertEquals(cellsInRange.get(5).getPosition(), new Vector(2,1));
        assertEquals(cellsInRange.get(6).getPosition(), new Vector(0,2));
        assertEquals(cellsInRange.get(7).getPosition(), new Vector(1,2));
        assertEquals(cellsInRange.get(8).getPosition(), new Vector(2,2));
    }

    @Test
    public void canGiveCellsInRangeInBorderlessOcean(){
        //Array
        CellsBehavior cellsBehavior = new BorderlessCellBehavior();

        //Act
        List<RelativeCell> cellsInRange = cellsBehavior.getCellsInRange(mockCellGrid, positionCenter, range);

        /*
        ocean
         00 10 20 30 40
         01 11 21 31 41
         02 12 22 32 42
         03 13 23 33 43
         04 14 24 34 44

         in range
        33 43 03 13 23
        34 44 04 14 24
        30 40 00 10 20
        31 41 01 11 21
        32 42 02 12 22
         */

        //Assert
        assertEquals(cellsInRange.get(0).getPosition(), new Vector(3,3));
        assertEquals(cellsInRange.get(1).getPosition(), new Vector(4,3));
        assertEquals(cellsInRange.get(2).getPosition(), new Vector(0,3));
        assertEquals(cellsInRange.get(3).getPosition(), new Vector(1,3));
        assertEquals(cellsInRange.get(4).getPosition(), new Vector(2,3));

        assertEquals(cellsInRange.get(5).getPosition(), new Vector(3,4));
        assertEquals(cellsInRange.get(6).getPosition(), new Vector(4,4));
        assertEquals(cellsInRange.get(7).getPosition(), new Vector(0,4));
        assertEquals(cellsInRange.get(8).getPosition(), new Vector(1,4));
        assertEquals(cellsInRange.get(9).getPosition(), new Vector(2,4));

        assertEquals(cellsInRange.get(10).getPosition(), new Vector(3,0));
        assertEquals(cellsInRange.get(11).getPosition(), new Vector(4,0));
        assertEquals(cellsInRange.get(12).getPosition(), new Vector(0,0));
        assertEquals(cellsInRange.get(13).getPosition(), new Vector(1,0));
        assertEquals(cellsInRange.get(14).getPosition(), new Vector(2,0));

        assertEquals(cellsInRange.get(15).getPosition(), new Vector(3,1));
        assertEquals(cellsInRange.get(16).getPosition(), new Vector(4,1));
        assertEquals(cellsInRange.get(17).getPosition(), new Vector(0,1));
        assertEquals(cellsInRange.get(18).getPosition(), new Vector(1,1));
        assertEquals(cellsInRange.get(19).getPosition(), new Vector(2,1));

        assertEquals(cellsInRange.get(20).getPosition(), new Vector(3,2));
        assertEquals(cellsInRange.get(21).getPosition(), new Vector(4,2));
        assertEquals(cellsInRange.get(22).getPosition(), new Vector(0,2));
        assertEquals(cellsInRange.get(23).getPosition(), new Vector(1,2));
        assertEquals(cellsInRange.get(24).getPosition(), new Vector(2,2));
    }

    @Test
    public void canGiveNewPositionBorderless(){
        //Array
        /*
        1 - current
        2 - target
        0) <- -1
        000
        102
        000
        1) -> +1
        000
        201
        000
        2) /\ -1
        010
        000
        020
        3) \/ +1
        020
        000
        010
         */
        List<Vector> currentPositions = new LinkedList<>();
        List<Vector> nextPosition = new LinkedList<>();
        List<Vector> directions = new LinkedList<>();

        currentPositions.add(new Vector(0,1));
        directions.add(new Vector(-1,0));
        nextPosition.add(new Vector(2,1));

        currentPositions.add(new Vector(2,1));
        directions.add(new Vector(1,0));
        nextPosition.add(new Vector(0,1));

        currentPositions.add(new Vector(1,0));
        directions.add(new Vector(0,-1));
        nextPosition.add(new Vector(1,2));

        currentPositions.add(new Vector(1,2));
        directions.add(new Vector(0,1));
        nextPosition.add(new Vector(1,0));

        CellsBehavior cellsBehavior = new BorderlessCellBehavior();

        CellGrid mockCellGrid = mock(CellGrid.class);
        when(mockCellGrid.getSize()).thenReturn(new Vector(3,3));


        for (int i =0;i<currentPositions.size();i++){

            //Act
            //Assert
            Assert.assertEquals(nextPosition.get(i), cellsBehavior.getNewPosition(mockCellGrid,currentPositions.get(i),directions.get(i)));
        }
    }
}

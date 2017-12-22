package model.grid;

import model.cell.implementation.DefaultCell;
import model.cell.interfaces.Cell;
import model.grid.implementation.DefaultCellGrid;
import model.parameters.Vector;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class DefaultCellGridTests {

    @Test
    public void canGiveCellByVector(){
        //Array
        Vector cellVector = new Vector(1,1);
        Cell mockCell = mock(Cell.class);
        Cell[][] cells = new Cell[3][3];
        cells[1][1] = mockCell;
        DefaultCellGrid cellGrid = new DefaultCellGrid(cells);

        //Act
        //Assert
        Assert.assertEquals(mockCell, cellGrid.getCell(cellVector));

    }
}

package model.ocean.implementaion;


import model.cell.implementation.DefaultRelativeCell;
import model.cell.interfaces.RelativeCell;
import model.grid.interfaces.CellGrid;
import model.ocean.interfaces.CellsBehavior;
import model.parameters.Vector;

import java.util.LinkedList;
import java.util.List;

public class BorderedCellBehavior implements CellsBehavior {


    @Override
    public List<RelativeCell> getCellsInRange(CellGrid cellGrid, Vector position, Integer range) {

        List<RelativeCell> relativeCells = new LinkedList<>();

        for (int relativeY = -range; relativeY <= range; relativeY++)
            for (int relativeX = -range; relativeX <= range; relativeX++) {
                int positionX = position.getX() + relativeX;
                int positionY = position.getY() + relativeY;

                if (positionX >= 0 && positionX < cellGrid.getSize().getX()
                        && positionY >= 0 && positionY < cellGrid.getSize().getY()) {

                    relativeCells.add(
                            new DefaultRelativeCell(
                                    cellGrid.getCell(new Vector(positionX, positionY))
                                    , new Vector(relativeX, relativeY)));
                }
            }
        return relativeCells;
    }

    @Override
    public void resolveBorderCells(DefaultOcean ocean) {

    }
}

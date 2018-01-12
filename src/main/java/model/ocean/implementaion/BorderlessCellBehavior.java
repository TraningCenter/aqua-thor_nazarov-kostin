package model.ocean.implementaion;

import model.cell.implementation.DefaultRelativeCell;
import model.cell.interfaces.RelativeCell;
import model.fish.interfaces.Fish;
import model.grid.interfaces.CellGrid;
import model.ocean.interfaces.CellsBehavior;
import model.ocean.interfaces.Ocean;
import model.parameters.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * interface for cells behavior, manages cells in borderless space
 */
public class BorderlessCellBehavior implements CellsBehavior {
    @Override
    public List<RelativeCell> getCellsInRange(CellGrid cellGrid, Vector position, Integer range) {

        List<RelativeCell> relativeCells = new LinkedList<>();

        for (int relativeY = -range; relativeY <= range; relativeY++)
            for (int relativeX = -range; relativeX <= range; relativeX++) {
                int positionX = position.getX() + relativeX;
                int positionY = position.getY() + relativeY;

                positionX = getAfterBorderPosition(positionX, cellGrid.getSize().getX(), 0);
                positionY = getAfterBorderPosition(positionY, cellGrid.getSize().getY(), 0);

                relativeCells.add(
                        new DefaultRelativeCell(
                                cellGrid.getCell(new Vector(positionX, positionY))
                                , new Vector(relativeX, relativeY)));
            }

        return relativeCells;
    }

    private int getAfterBorderPosition(int position, Integer size, Integer offset) {
        if (position - offset < 0)
            position = size + position;
        else if (position + offset>= size)
            position = (position) % size;
        return position;
    }

    @Override
    public Vector getNewPosition(CellGrid cellGrid, Vector position, Vector direction) {
        int nextX = position.getX() + direction.getX();
        int nextY = position.getY() + direction.getY();

        if (nextX<0)
            nextX=cellGrid.getSize().getX()+nextX;
        else if (nextX>=cellGrid.getSize().getX())
            nextX=cellGrid.getSize().getX()%nextX;

        if (nextY<0)
            nextY=cellGrid.getSize().getY()+nextY;
        else if (nextY>=cellGrid.getSize().getY())
            nextY=cellGrid.getSize().getY()%nextY;

        return new Vector(nextX,nextY);
    }
}

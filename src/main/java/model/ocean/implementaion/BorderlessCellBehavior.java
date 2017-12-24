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
    public void resolveBorderCells(Ocean ocean) {
        Stream<Fish> fishesOnBorder = ocean.getFishes().stream().filter(fish -> checkIfOnBorder(fish, ocean.getCellGrid()));
        fishesOnBorder.forEach(fish -> fish.setCurrentPosition(calculateAfterBorderPosition(fish.getCurrentPosition(), ocean.getCellGrid())));
    }

    private Vector calculateAfterBorderPosition(Vector currentPosition, CellGrid grid) {
        int positionX = getAfterBorderPosition(currentPosition.getX(), grid.getSize().getX()-1, 1);
        int positionY = getAfterBorderPosition(currentPosition.getY(), grid.getSize().getY()-1, 1);

        return new Vector(positionX, positionY);
    }

    private boolean checkIfOnBorder(Fish fish, CellGrid grid){
        return fish.getCurrentPosition().getX() == 0 || fish.getCurrentPosition().getX() == grid.getSize().getX()-1 ||
                fish.getCurrentPosition().getY() == 0 || fish.getCurrentPosition().getY() == grid.getSize().getY()-1;
    }
}

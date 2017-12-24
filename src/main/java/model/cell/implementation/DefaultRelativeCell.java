package model.cell.implementation;

import model.cell.interfaces.Cell;
import model.cell.interfaces.RelativeCell;
import model.fish.interfaces.Fish;
import model.parameters.Vector;

import java.util.List;

public class DefaultRelativeCell implements RelativeCell {

    private Vector relativePosition;
    private Cell cell;

    public  DefaultRelativeCell(Cell cell, Vector relativePosition) {
        this.cell = cell;
        this.relativePosition = relativePosition;
    }

    @Override
    public Vector getRelativePosition() {
        return relativePosition;
    }

    @Override
    public Vector getPosition() {
        return cell.getPosition();
    }

    @Override
    public void add(Fish fish) {
        cell.add(fish);
    }

    @Override
    public void removeFish(Fish fish) {
        cell.removeFish(fish);
    }

    @Override
    public List<Fish> getFishes() {
        return cell.getFishes();
    }
}

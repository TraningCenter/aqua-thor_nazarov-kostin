package model.ocean;

import model.cell.interfaces.Cell;
import model.cell.interfaces.RelativeCell;
import model.fish.interfaces.Fish;
import model.ocean.interfaces.OceanSpace;
import model.parameters.Vector;

import java.util.List;

public class OceanSpaceMock implements OceanSpace {


    @Override
    public List<RelativeCell> getCellsInRange(Vector position, Integer range) {
        return null;
    }

    @Override
    public Integer getFlowStrength(Vector position, Vector direction) {
        return null;
    }

    @Override
    public void addFish(Fish fish) {

    }

    @Override
    public void removeFish(Fish fish) {

    }

    @Override
    public Cell getCell(Vector position) {
        return null;
    }

    @Override
    public Vector getOceanSize() {
        return null;
    }

    @Override
    public Vector getNewPosition(Vector position, Vector direction) {
        return null;
    }

    @Override
    public Vector getFlowDirection(Vector position) {
        return null;
    }
}

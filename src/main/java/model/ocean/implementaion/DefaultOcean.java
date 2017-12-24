package model.ocean.implementaion;

import model.cell.interfaces.RelativeCell;
import model.fish.interfaces.Fish;
import model.fish.interfaces.OceanFishState;
import model.grid.interfaces.CellGrid;
import model.ocean.interfaces.CellsBehavior;
import model.ocean.interfaces.Ocean;
import model.ocean.interfaces.OceanSpace;
import model.parameters.Flow;
import model.parameters.Vector;

import java.util.LinkedList;
import java.util.List;

public class DefaultOcean implements Ocean, OceanSpace {

    private CellsBehavior cellsBehavior;

    private List<Flow> flows;
    private CellGrid grid;

    private List<Fish> fishes = new LinkedList<>();

    public DefaultOcean(CellsBehavior cellsBehavior, List<Flow> flows, CellGrid grid) {
        this.cellsBehavior = cellsBehavior;
        this.flows = flows;
        this.grid = grid;
    }

    @Override
    public void update() {

    }

    @Override
    public List<Fish> getFishes() {
        return null;
    }

    @Override
    public List<RelativeCell> getCellsInRange(Vector position, Integer range) {
        return null;
    }

    @Override
    public Vector modifyVelocity(Vector baseVelocity) {
        return null;
    }

    @Override
    public void addFish(Fish fish) {

    }
}

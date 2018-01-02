package model.ocean.implementaion;

import model.cell.interfaces.Cell;
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
        return fishes;
    }

    @Override
    public CellGrid getCellGrid() {
        return grid;
    }

    @Override
    public List<RelativeCell> getCellsInRange(Vector position, Integer range) {
        return cellsBehavior.getCellsInRange(grid,position,range);
    }

    @Override
    public Vector modifyVelocity(Vector baseVelocity, Vector position) {
        final Integer[] xVelocity = {baseVelocity.getX()};
        final Integer[] yVelocity = {baseVelocity.getY()};

        flows.stream().filter(flow -> flow.getRectangle().isInside(position)).forEach(flow -> {
                xVelocity[0] +=(flow.getDirection().getX()>0?1:flow.getDirection().getX()==0?0:-1)*flow.getStrength();
                yVelocity[0] +=(flow.getDirection().getY()>0?1:flow.getDirection().getY()==0?0:-1)*flow.getStrength();
        });

        return new Vector(xVelocity[0],yVelocity[0]);
    }

    @Override
    public void addFish(Fish fish) {

    }

    @Override
    public Cell getCell(Vector position) {
        return this.grid.getCell(position);
    }
}

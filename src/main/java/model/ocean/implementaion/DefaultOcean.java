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
        this.fishes.forEach(fish -> fish.action());
    }

    @Override
    public List<Fish> getFishes() {
        return fishes;
    }

    @Override
    public CellGrid getCellGrid() {
        return this.grid;
    }

    @Override
    public List<RelativeCell> getCellsInRange(Vector position, Integer range) {
        return this.cellsBehavior.getCellsInRange(grid,position,range);
    }

    @Override
    public Integer getFlowStrength(Vector position, Vector direction) {
        final Integer[] xVelocity = {0};
        final Integer[] yVelocity = {0};

        flows.stream().filter(flow -> flow.getRectangle().isInside(position)).forEach(flow -> {
            xVelocity[0] +=(flow.getDirection().getX()>0?1:flow.getDirection().getX()==0?0:-1)*flow.getStrength();
            yVelocity[0] +=(flow.getDirection().getY()>0?1:flow.getDirection().getY()==0?0:-1)*flow.getStrength();
        });

        if (direction.getX()!=0)
            return xVelocity[0];
        else
            return yVelocity[0];
    }

    @Override
    public void addFish(Fish fish) {
        this.fishes.add(fish);
    }

    @Override
    public Cell getCell(Vector position) {
        return this.grid.getCell(position);
    }

    @Override
    public Vector getOceanSize() {
        return grid.getSize();
    }

    @Override
    public Vector getNewPosition(Vector position, Vector direction) {
        return this.cellsBehavior.getNewPosition(this.grid,position,direction);
    }
}

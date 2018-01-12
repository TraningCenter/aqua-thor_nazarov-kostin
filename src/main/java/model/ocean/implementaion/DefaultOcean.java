package model.ocean.implementaion;

import model.cell.interfaces.Cell;
import model.cell.interfaces.RelativeCell;
import model.fish.interfaces.Fish;
import model.grid.interfaces.CellGrid;
import model.ocean.interfaces.CellsBehavior;
import model.ocean.interfaces.Ocean;
import model.ocean.interfaces.OceanSpace;
import model.parameters.Flow;
import model.parameters.Vector;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * implements main ocean logic, contains fishes, flows, cell grid, manages different physic conditions
 */
public class DefaultOcean implements Ocean, OceanSpace {

    private CellsBehavior cellsBehavior;

    private List<Flow> flows;
    private CellGrid grid;

    private List<Fish> fishes = new LinkedList<>();
    private List<Fish> deadFishes = new LinkedList<>();

    public DefaultOcean(CellsBehavior cellsBehavior, List<Flow> flows, CellGrid grid) {
        this.cellsBehavior = cellsBehavior;
        this.flows = flows;
        this.grid = grid;
    }

    public DefaultOcean(CellsBehavior cellsBehavior, List<Flow> flows, CellGrid grid, Collection<Fish> fishCollection) {
        this.cellsBehavior = cellsBehavior;
        this.flows = flows;
        this.grid = grid;
        fishes.addAll(fishCollection);
    }

    @Override
    public void update() {
        for (int i=0; i<fishes.size();i++){
            fishes.get(i).action();
        }

        removeDeadFishes();
    }

    private void removeDeadFishes() {
        if (this.deadFishes.size()==0)
            return;

        this.deadFishes.forEach(fish -> this.grid.getCell(fish.getCurrentPosition()).removeFish(fish));

        fishes.removeAll(this.deadFishes);

        this.deadFishes.clear();
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

        return flows.stream()
                .filter(flow -> flow.getDirection().equalsDimension(direction))
                .map(flow -> ((flow.getDirection().isSameDimensionDirection(direction)?1:-1) * flow.getStrength()))
                .findFirst()
                .orElse(0);
    }

    @Override
    public void addFish(Fish fish) {
        List<Fish> fishesInCurrentCell = this.grid.getCell(fish.getCurrentPosition()).getFishes();
        if (!fishesInCurrentCell.contains(fish))
            fishesInCurrentCell.add(fish);

        this.fishes.add(fish);
    }

    @Override
    public void removeFish(Fish fish) {
       // this.grid.getCell(fish.getCurrentPosition()).removeFish(fish);
        this.deadFishes.add(fish);
    }

    public void addAllFishes(Collection<Fish> fishCollection){ this.fishes.addAll(fishCollection); }

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

    @Override
    public Vector getFlowDirection(Vector position) {
        for (int i = 0; i < flows.size(); i++) {
            if (flows.get(i).getRectangle().isInside(position))
                return flows.get(i).getDirection();
        }
        return Vector.Zero();
    }

    public List<Flow> getFlows() {
        return flows;
    }

    public CellsBehavior getCellsBehavior() {
        return cellsBehavior;
    }
}

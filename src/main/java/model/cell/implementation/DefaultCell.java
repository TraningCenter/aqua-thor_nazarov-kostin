package model.cell.implementation;

import model.cell.interfaces.Cell;
import model.fish.interfaces.Fish;
import model.parameters.Vector;

import java.util.*;

/**
 * class with data for one cell
 */
public class DefaultCell implements Cell {

    private Vector position;
    private Set<Fish> fishes = new LinkedHashSet<>();

    public DefaultCell(Vector position) {

        this.position = position;
    }

    @Override
    public Vector getPosition() {
        return new Vector(position);
    }

    @Override
    public void add(Fish fish) {
        fishes.add(fish);
    }

    @Override
    public void removeFish(Fish fish) {
        fishes.remove(fish);
    }

    @Override
    public List<Fish> getFishes() {

        return new LinkedList<>(fishes);
    }
}

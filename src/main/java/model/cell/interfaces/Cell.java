package model.cell.interfaces;

import model.fish.interfaces.Fish;
import model.parameters.Vector;

import java.util.List;
/**
 * interface with data for one cell
 */
public interface Cell {
    Vector getPosition();
    void add(Fish fish);
    void removeFish(Fish fish);
    List<Fish> getFishes();
}

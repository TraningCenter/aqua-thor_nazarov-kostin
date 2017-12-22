package model.cell.interfaces;

import model.fish.interfaces.Fish;
import model.parameters.Vector;

import java.util.List;

public interface Cell {
    Vector getPosition();
    void add(Fish fish);
    void removeFish(Fish fish);
    List<Fish> getFishes();
}

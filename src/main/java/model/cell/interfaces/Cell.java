package model.cell.interfaces;

import model.fish.interfaces.Fish;

import java.util.List;

public interface Cell {
    void add(Fish fish);
    void removeFish(Fish fish);
    List<Fish> getFishes();
}

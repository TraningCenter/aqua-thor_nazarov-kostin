package model.ocean.interfaces;

import model.fish.interfaces.Fish;
import model.grid.interfaces.CellGrid;

import java.util.List;

public interface Ocean {
    void update();
    List<Fish> getFishes();
    CellGrid getCellGrid();
}


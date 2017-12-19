package model.ocean.interfaces;

import model.fish.interfaces.Fish;

import java.util.List;

public interface Ocean {
    void update();
    List<Fish> getFishes();
}


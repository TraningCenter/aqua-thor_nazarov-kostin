package model.fish.implementation;

import model.cell.interfaces.Cell;
import model.fish.interfaces.EatingOceanFishStrategy;
import model.fish.interfaces.Fish;
import model.ocean.interfaces.OceanSpace;

import java.util.List;
import java.util.stream.Collectors;

public class AggressiveEatingOceanFishStrategy implements EatingOceanFishStrategy {

    @Override
    public void eatIfNeed(OceanFish oceanFish, OceanSpace oceanSpace) {
        List<Fish> fishes = oceanSpace.getCell(oceanFish.getCurrentPosition())
                .getFishes().stream().filter(fish -> fish.getType() == FishType.PASSIVE).collect(Collectors.toList());

        if (fishes.size()==0)
            return;

        oceanSpace.removeFish(fishes.get(0));
        oceanFish.changeState(new EatingOceanFishState(oceanFish));
    }
}

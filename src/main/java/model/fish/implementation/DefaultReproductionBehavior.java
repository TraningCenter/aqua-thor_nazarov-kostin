package model.fish.implementation;

import model.fish.interfaces.ReproductionBehavior;
import model.ocean.interfaces.OceanSpace;
import model.parameters.Vector;

import java.util.function.Supplier;

/**
 * Reproduction behavior for fishes, create new fish on birth position,
 * takes fishSupplier which creates new fish
 */
public class DefaultReproductionBehavior implements ReproductionBehavior {

    private Supplier<OceanFish> fishSupplier;

    public DefaultReproductionBehavior(Supplier<OceanFish> fishSupplier) {
        this.fishSupplier = fishSupplier;
    }

    @Override
    public void birth(OceanSpace ocean, Vector birthPosition) {
        OceanFish fish = fishSupplier.get();
        fish.changeState(new BirthOceanFishState(fish));
        fish.setCurrentPosition(birthPosition);
        ocean.addFish(fish);
    }
}

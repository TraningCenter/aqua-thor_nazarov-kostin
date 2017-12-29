package model.fish;

import model.fish.implementation.*;
import model.fish.interfaces.ReproductionBehavior;
import model.fish.interfaces.TargetCalculationFishStrategy;
import model.ocean.interfaces.OceanSpace;
import model.parameters.Vector;

import static org.mockito.Mockito.mock;

public class Util {

    public static OceanFish createOceanFishInMocks(Vector startPosition, Target currentTarget){

        ReproductionBehavior mockReproductionBehavior = mock(ReproductionBehavior.class);
        BorderedMoveToTargetStrategy borderedMoveToTargetStrategy = new BorderedMoveToTargetStrategy();
        TargetCalculationFishStrategy targetCalculationFishStrategy = mock(TargetCalculationFishStrategy.class);
        OceanSpace mockOceanSpace = mock(OceanSpace.class);

        OceanFish oceanFish = new OceanFish(FishType.PASSIVE, startPosition,
                mockOceanSpace, new DoingNothingOceanFishState(),
                targetCalculationFishStrategy,
                mockReproductionBehavior,
                borderedMoveToTargetStrategy);

        MovingOceanFishState startState = new MovingOceanFishState(oceanFish);
        startState.setCurrentTarget(currentTarget);
        oceanFish.changeState(startState);

        return oceanFish;
    }
}

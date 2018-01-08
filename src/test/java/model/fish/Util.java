package model.fish;

import model.fish.implementation.*;
import model.fish.implementation.target.Target;
import model.fish.interfaces.ReproductionBehavior;
import model.fish.interfaces.target.TargetCalculationFishStrategy;
import model.fish.interfaces.target.TargetCellPredicate;
import model.fish.interfaces.target.TargetPriorityCalcFunction;
import model.ocean.interfaces.OceanSpace;
import model.parameters.FishParameters;
import model.parameters.Vector;

import static org.mockito.Mockito.mock;

public class Util {

    public static OceanFish createOceanFishInMocks(Vector startPosition, Target currentTarget){

        ReproductionBehavior mockReproductionBehavior = mock(ReproductionBehavior.class);
        BorderedMoveToTargetStrategy borderedMoveToTargetStrategy = new BorderedMoveToTargetStrategy();
        TargetCalculationFishStrategy targetCalculationFishStrategy = mock(TargetCalculationFishStrategy.class);
        OceanSpace mockOceanSpace = mock(OceanSpace.class);

        OceanFish oceanFish = new OceanFish(FishType.PASSIVE,
                mock(FishParameters.class),
                startPosition,
                mockOceanSpace, new DoingNothingOceanFishState(),
                targetCalculationFishStrategy,
                mockReproductionBehavior,
                borderedMoveToTargetStrategy,
                mock(TargetCellPredicate.class),
                mock(TargetPriorityCalcFunction.class));

        MovingOceanFishState startState = new MovingOceanFishState(oceanFish);
        startState.setCurrentTarget(currentTarget);
        oceanFish.changeState(startState);

        return oceanFish;
    }
}

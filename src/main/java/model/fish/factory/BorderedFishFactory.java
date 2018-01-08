package model.fish.factory;

import model.fish.implementation.*;
import model.fish.implementation.target.PassiveTargetCellPredicate;
import model.fish.implementation.target.RandomTargetCalculationFishStrategy;
import model.fish.implementation.target.TargetPriority;
import model.fish.interfaces.Fish;
import model.ocean.interfaces.OceanSpace;
import model.parameters.FishParameters;
import model.parameters.Vector;

public class BorderedFishFactory implements FishFactory {

    @Override
    public Fish createFish(FishType fishType, FishParameters fishParameters, Vector startPosition, OceanSpace oceanSpace) {
        switch (fishType)
        {
            case PASSIVE:
                return createPassiveFish(startPosition, fishParameters, oceanSpace);
            case AGGRESSIVE:
                break;
        }
        return null;
    }

    private Fish createPassiveFish(Vector startPosition, FishParameters fishParameters, OceanSpace oceanSpace){
        return new OceanFish(FishType.PASSIVE,
                fishParameters,
                startPosition,
                oceanSpace,
                new DoingNothingOceanFishState(),
                new RandomTargetCalculationFishStrategy(),
                new DefaultReproductionBehavior(),
                new BorderedMoveToTargetStrategy(),
                new PassiveTargetCellPredicate(),
                relativeCell -> TargetPriority.HIGH);
    }


    //TODO: aggressive fish
}

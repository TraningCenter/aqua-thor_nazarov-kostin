package model.fish.factory;

import model.fish.implementation.*;
import model.fish.implementation.target.*;
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
                return createAggressiveFish(startPosition, fishParameters, oceanSpace);
        }
        return null;
    }

    private OceanFish createPassiveFish(Vector startPosition, FishParameters fishParameters, OceanSpace oceanSpace){
        OceanFish fish =  new OceanFish(FishType.PASSIVE,
                fishParameters,
                startPosition,
                oceanSpace,
                new DoingNothingOceanFishState(),
                new EscapeTargetCalculationFishStrategy(),
                new DefaultReproductionBehavior(() -> createPassiveFish(Vector.Zero(), fishParameters, oceanSpace)),
                new BorderedMoveToTargetStrategy(),
                new PassiveTargetCellPredicate(),
                relativeCell -> TargetPriority.HIGH,
                new PassiveEatingOceanFishStrategy());
        fish.changeState(new BirthOceanFishState(fish));
        return fish;
    }

    private OceanFish createAggressiveFish(Vector startPosition, FishParameters fishParameters, OceanSpace oceanSpace){
        OceanFish fish =  new OceanFish(FishType.AGGRESSIVE,
                fishParameters,
                startPosition,
                oceanSpace,
                new DoingNothingOceanFishState(),
                new HuntingTargetCalculationFishStrategy(),
                new DefaultReproductionBehavior(() -> createAggressiveFish(Vector.Zero(), fishParameters, oceanSpace)),
                new BorderedMoveToTargetStrategy(),
                new AggressiveTargetCellPredicate(),
                relativeCell -> TargetPriority.HIGH,
                new AggressiveEatingOceanFishStrategy());
        fish.changeState(new BirthOceanFishState(fish));
        return fish;
    }

}

package configurator.implementation;

import configurator.interfaces.FishesCreator;
import model.fish.factory.BorderedFishFactory;
import model.fish.factory.BorderlessFishFactory;
import model.fish.factory.FishFactory;
import model.fish.implementation.*;
import model.fish.implementation.target.EscapeTargetCalculationFishStrategy;
import model.fish.implementation.target.HuntingTargetCalculationFishStrategy;
import model.fish.interfaces.Fish;
import model.fish.interfaces.target.TargetCalculationFishStrategy;
import model.ocean.interfaces.OceanSpace;
import model.parameters.FishParameters;
import model.parameters.OceanParameters;
import model.parameters.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DefaultFishesCreator implements FishesCreator {

    private List<Fish> fishes;

    @Override
    public List<Fish> createFishes(OceanParameters oceanParameters, OceanSpace oceanSpace) {
        fishes = new ArrayList<>();
        oceanParameters.getOceanSize();
        for(int i=0;i<oceanParameters.getPassiveFishCount();i++){
            fishes.add(createOneFish(oceanParameters,oceanSpace,true));
        }
        for (int i=0;i<oceanParameters.getAggressiveFishCount();i++){
            fishes.add(createOneFish(oceanParameters,oceanSpace,false));
        }
        return  fishes;
    }

    private Fish createOneFish(OceanParameters oceanParameters , OceanSpace oceanSpace,boolean isPassive){
        FishParameters parameters = isPassive?oceanParameters.getPassiveFishParameters():oceanParameters.getAggressiveFishParameters();
        Random rnd = new Random();
        int x =rnd.nextInt(oceanParameters.getOceanSize().getX());
        int y =rnd.nextInt(oceanParameters.getOceanSize().getY());
        Vector startPosition = new Vector(x,y);
        FishFactory factory=null;
      TargetCalculationFishStrategy targetCalcStrategy = isPassive?(new EscapeTargetCalculationFishStrategy()):(new HuntingTargetCalculationFishStrategy());
        switch (oceanParameters.getOceanType()){
            case BORDERED:
                factory = new BorderedFishFactory();
                break;
            case BORDERLESS:
               factory = new BorderlessFishFactory();
               break;
        }

        FishType type = isPassive?FishType.PASSIVE:FishType.AGGRESSIVE;
   /*      Fish fish = new OceanFish(type,parameters,startPosition,oceanSpace,new DoingNothingOceanFishState(),targetCalcStrategy,new DefaultReproductionBehavior(),) */
       return factory.createFish(type,parameters,startPosition,oceanSpace);

    }
}

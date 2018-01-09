package model.fish;

import model.fish.implementation.BirthOceanFishState;
import model.fish.implementation.MovingOceanFishState;
import model.fish.implementation.OceanFish;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class BirthOceanFishStateTests {

   // @Test
    public void canChangeStateToMovingAfterTicks(){
        //Array
        OceanFish mockOceanFish = mock(OceanFish.class);
        Integer ticksToBecomeAdult = 5;

        BirthOceanFishState birthOceanFishState = new BirthOceanFishState(mockOceanFish);

        //Act
        for (int i=0;i<5;i++){
            birthOceanFishState.action();
        }

        //Assert
        verify(mockOceanFish).changeState(any(MovingOceanFishState.class));
    }
}

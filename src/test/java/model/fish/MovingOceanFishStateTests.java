package model.fish;

import model.fish.implementation.MovingOceanFishState;
import model.fish.implementation.OceanFish;
import model.parameters.Vector;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MovingOceanFishStateTests {

    @Test
    public void canFindTargetAndMoveToIt(){
        //Array
        OceanFish mockOceanFish = mock(OceanFish.class);

        MovingOceanFishState movingOceanFishState = new MovingOceanFishState(mockOceanFish);

        //Act
        movingOceanFishState.action();
        movingOceanFishState.action();
        movingOceanFishState.action();

        //Assert
        verify(mockOceanFish).calculateTargetPosition();
        verify(mockOceanFish, times(3)).move(any(Vector.class));
    }
}

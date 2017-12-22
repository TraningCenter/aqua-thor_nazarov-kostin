package model.fish;

import model.fish.implementation.MovingOceanFishState;
import model.fish.implementation.OceanFish;
import model.fish.implementation.Target;
import model.fish.implementation.TargetPriority;
import model.parameters.Vector;
import org.junit.Test;
import org.mockito.verification.VerificationMode;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MovingOceanFishStateTests {

    @Test
    public void canFindTargetAndMoveToIt(){
        //Array
        OceanFish mockOceanFish = mock(OceanFish.class);
        when(mockOceanFish.calculateTargetPosition()).thenReturn(new Target(new Vector(0,1), TargetPriority.HIGH));

        MovingOceanFishState movingOceanFishState = new MovingOceanFishState(mockOceanFish);

        //Act
        movingOceanFishState.action();
        movingOceanFishState.action();
        movingOceanFishState.action();

        //Assert
        verify(mockOceanFish, atLeastOnce()).calculateTargetPosition();
        verify(mockOceanFish, times(3)).moveToTarget(any(Target.class));
    }
}

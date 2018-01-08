package model.fish;

import model.fish.implementation.MovingOceanFishState;
import model.fish.implementation.OceanFish;
import model.fish.implementation.target.Target;
import model.fish.implementation.target.TargetPriority;
import model.parameters.FishParameters;
import model.parameters.Vector;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MovingOceanFishStateTests {

    @Test
    public void canFindTargetAndMoveToIt(){
        //Array
        OceanFish mockOceanFish = mock(OceanFish.class);
        when(mockOceanFish.calculateTargetPosition()).thenReturn(new Target(new Vector(0,1), TargetPriority.HIGH));
        when(mockOceanFish.getTimeToMoveToPosition(any(Vector.class))).thenReturn(2);

        MovingOceanFishState movingOceanFishState = new MovingOceanFishState(mockOceanFish);

        //Act
        for (int i=0;i<12;i++)
            movingOceanFishState.action();

        //Assert
        verify(mockOceanFish, atLeastOnce()).calculateTargetPosition();
        verify(mockOceanFish, times(4)).moveToTarget(any(Target.class));
    }
}

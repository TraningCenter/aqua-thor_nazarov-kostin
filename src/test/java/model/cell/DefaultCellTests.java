package model.cell;

import model.cell.implementation.DefaultCell;
import model.fish.interfaces.Fish;
import model.parameters.Vector;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class DefaultCellTests {

    @Test
    public void canAddAndRemoveFishes(){
        //Array
        Vector position = new Vector(1,1);
        DefaultCell defaultCell = new DefaultCell(position);

        Fish fish1 = mock(Fish.class);
        Fish fish2 = mock(Fish.class);
        Fish fish3 = mock(Fish.class);

        //Act
        defaultCell.add(fish1);
        defaultCell.add(fish2);
        defaultCell.add(fish3);
        defaultCell.removeFish(fish3);
        defaultCell.removeFish(fish1);

        //Assert
        Assert.assertEquals(fish2, defaultCell.getFishes().get(0));

    }
}

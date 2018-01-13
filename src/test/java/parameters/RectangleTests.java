package parameters;

import model.parameters.Rectangle;
import model.parameters.Vector;
import org.junit.Assert;
import org.junit.Test;

public class RectangleTests {

    @Test
    public void canCheckIfInside(){
        //Array
        Vector first = new Vector(0,0);
        Vector second = new Vector(5,5);
        Vector third = new Vector(3,3);
        Rectangle rectangle = new Rectangle(1,1,3,3);

        //Act
        boolean firstInside = rectangle.isInside(first);
        boolean secondInside = rectangle.isInside(second);
        boolean thirdInside = rectangle.isInside(third);

        //Assert
        Assert.assertEquals(false, firstInside);
        Assert.assertEquals(false, secondInside);
        Assert.assertEquals(true, thirdInside);
    }
}

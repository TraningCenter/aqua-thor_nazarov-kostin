package parameters;

import model.parameters.Vector;
import org.junit.Assert;
import org.junit.Test;

public class VectorTests {

    @Test
    public void canCalculateDistance(){
        //Array
        Vector first = new Vector(1,1);
        Vector second = new Vector(2,2);
        Double distanceExpected = Math.sqrt(2);

        //Act
        Double distanceResult = first.distanceTo(second);

        //Assert
        Assert.assertEquals(distanceExpected,distanceResult);
    }

    @Test
    public void canMinusVectors(){
        Vector first = new Vector(1,1);
        Vector second = new Vector(3,3);
        Vector minusExpected = new Vector(2,2);

        Vector minusResult = second.minus(first);

        Assert.assertEquals(minusExpected, minusResult);
    }

    @Test
    public void canPlusVectors(){
        Vector first = new Vector(5,5);
        Vector second = new Vector(3,0);
        Vector plusExpected = new Vector(8,5);

        Vector plusResult = second.plus(first);

        Assert.assertEquals(plusExpected, plusResult);
    }

    @Test
    public void canConvertToString(){
        Vector first = new Vector(5,5);
        String stringExpected = "Vector{x=5, y=5}";

        String stringResult = first.toString();

        Assert.assertEquals(stringExpected, stringResult);
    }
}

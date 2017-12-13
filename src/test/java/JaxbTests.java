import model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parsers.dom.JaxbReader;
import parsers.dom.JaxbWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class JaxbTests {

    String initXmlString;
    OceanParameters initOceanParameters;

    @Before
    public void init(){
        initXmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><oceanParameters><oceanType>BORDERLESS</oceanType><oceanSize><x>20</x><y>10</y></oceanSize><flow><direction><x>1</x><y>0</y></direction><strength>1</strength></flow><passiveFishCount>15</passiveFishCount><aggressiveFishCount>5</aggressiveFishCount><passiveFishParameters><lifeTimeTicks>100</lifeTimeTicks><reproductionPeriodTicks>10</reproductionPeriodTicks><smellSenseDistance>3</smellSenseDistance><starvationTimeTicks>10</starvationTimeTicks></passiveFishParameters><aggressiveFishParameters><lifeTimeTicks>150</lifeTimeTicks><reproductionPeriodTicks>140</reproductionPeriodTicks><smellSenseDistance>3</smellSenseDistance><starvationTimeTicks>5</starvationTimeTicks></aggressiveFishParameters></oceanParameters>";

        initOceanParameters = new OceanParameters();
        initOceanParameters.setOceanSize(new Vector(20, 10));
        initOceanParameters.setFlow(new Flow(new Vector(1, 0), 1));
        initOceanParameters.setPassiveFishCount(15);
        initOceanParameters.setAggressiveFishCount(5);
        initOceanParameters.setPassiveFishParameters(new FishParameters(10, 100, 10, 3));
        initOceanParameters.setAggressiveFishParameters(new FishParameters(140, 150, 5, 3));
        initOceanParameters.setOceanType(OceanType.BORDERLESS);
    }

    @Test
    public void canWrite() throws UnsupportedEncodingException {
        //Array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JaxbWriter writer = new JaxbWriter();

        //Act
        writer.write(initOceanParameters, outputStream);

        //Assert
        String xmlString = new String(outputStream.toByteArray(), "UTF-8");

        Assert.assertEquals(initXmlString, xmlString);
    }

    @Test
    public void canRead() throws UnsupportedEncodingException {
        //Array
        ByteArrayInputStream inputStream = new ByteArrayInputStream(initXmlString.getBytes(StandardCharsets.UTF_8.name()));
        JaxbReader reader = new JaxbReader();

        //Act
        OceanParameters OceanParameters = reader.read(inputStream);

        //Assert
        Assert.assertEquals(initOceanParameters, OceanParameters);
    }
}

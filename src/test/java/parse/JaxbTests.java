package parse;

import model.parameters.OceanParameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parsers.jaxb.JaxbOceanParamsReader;
import parsers.jaxb.JaxbOceanParamsWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class JaxbTests {

    String initXmlString;
    OceanParameters initOceanParameters;

    @Before
    public void init(){
        initXmlString = TestUtil.getTestXmlString();
        initOceanParameters = TestUtil.getTestOceanParameters();
    }

  //  @Test
    public void canWrite() throws UnsupportedEncodingException {
        //Array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JaxbOceanParamsWriter writer = new JaxbOceanParamsWriter();

        //Act
        writer.write(initOceanParameters, outputStream);

        //Assert
        String xmlString = new String(outputStream.toByteArray(), "UTF-8");

        Assert.assertEquals(initXmlString, xmlString);
    }

  //  @Test
    public void canRead() throws UnsupportedEncodingException {
        //Array
        ByteArrayInputStream inputStream = new ByteArrayInputStream(initXmlString.getBytes(StandardCharsets.UTF_8.name()));
        JaxbOceanParamsReader reader = new JaxbOceanParamsReader();

        //Act
        OceanParameters OceanParameters = reader.read(inputStream);

        //Assert
        Assert.assertEquals(initOceanParameters, OceanParameters);
    }
}

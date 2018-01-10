package parse;

import model.parameters.OceanParameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parsers.sax.SaxOceanParamsReader;
import parsers.sax.SaxOceanParamsWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class SaxTests {

    String initXmlString;
    OceanParameters initOceanParameters;
    String initBigXmlString;

    @Before
    public void init() {
        initXmlString = TestUtil.getTestXmlWithoutStandaloneString();
        initOceanParameters = TestUtil.getTestOceanParameters();
        initBigXmlString = TestUtil.getBigTestXmlString();
    }


   // @Test
    public void canRead() throws UnsupportedEncodingException {
        //Array

        ByteArrayInputStream inputStream = new ByteArrayInputStream(initXmlString.getBytes(StandardCharsets.UTF_8.name()));

        SaxOceanParamsReader reader = new SaxOceanParamsReader();

        //Act
        OceanParameters OceanParameters = reader.read(inputStream);

        //Assert
        Assert.assertEquals(initOceanParameters, OceanParameters);
    }

 //   @Test
    public void canReadBigData() throws UnsupportedEncodingException {
        //Array
        ByteArrayInputStream inputStream = new ByteArrayInputStream(initBigXmlString.getBytes(StandardCharsets.UTF_8.name()));

        SaxOceanParamsReader reader = new SaxOceanParamsReader();

        //Act
        OceanParameters OceanParameters = reader.read(inputStream);

        //Assert
        Assert.assertEquals(initOceanParameters, OceanParameters);
    }

  //  @Test
    public void canWrite() throws UnsupportedEncodingException {

        //Array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        SaxOceanParamsWriter writer = new SaxOceanParamsWriter();

        //Act
        writer.write(initOceanParameters, outputStream);

        //Assert
        String xmlString = new String(outputStream.toByteArray(), "UTF-8");

        Assert.assertEquals(initXmlString, xmlString);
    }
}

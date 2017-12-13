import model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parsers.dom.DomOceanParamsReader;
import parsers.jaxb.JaxbOceanParamsReader;
import parsers.jaxb.JaxbOceanParamsWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class DomTests {

    String initXmlString;
    OceanParameters initOceanParameters;

    @Before
    public void init(){
        initXmlString = TestUtil.getTestXmlString();
        initOceanParameters = TestUtil.getTestOceanParameters();
    }


    @Test
    public void canWrite() throws UnsupportedEncodingException {

    }

    @Test
    public void canRead() throws UnsupportedEncodingException {
        //Array
        ByteArrayInputStream inputStream = new ByteArrayInputStream(initXmlString.getBytes(StandardCharsets.UTF_8.name()));
        DomOceanParamsReader reader = new DomOceanParamsReader();

        //Act
        OceanParameters OceanParameters = reader.read(inputStream);

        //Assert
        Assert.assertEquals(initOceanParameters, OceanParameters);
    }

}

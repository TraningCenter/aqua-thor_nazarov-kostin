import model.OceanParameters;
import org.junit.Test;
import parsers.dom.JaxbWriter;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

public class JaxbWriterTests {

    @Test
    public void canWrite() throws UnsupportedEncodingException {
        OceanParameters oceanParameters = OceanParameters.getTestParams();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JaxbWriter writer = new JaxbWriter(outputStream);

        writer.write(oceanParameters);

        String xmlString = new String(outputStream.toByteArray(),"UTF-8");


    }
}

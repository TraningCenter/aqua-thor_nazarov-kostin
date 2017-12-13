package parsers.jaxb;

import model.OceanParameters;
import parsers.OceanParamsWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.OutputStream;

public class JaxbOceanParamsWriter implements OceanParamsWriter {

    public void write(OceanParameters oceanParameters, OutputStream outputStream) {
        try {
            JAXBContext jc = JAXBContext.newInstance(OceanParameters.class);
            Marshaller m = jc.createMarshaller();

            m.marshal(oceanParameters, outputStream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


}

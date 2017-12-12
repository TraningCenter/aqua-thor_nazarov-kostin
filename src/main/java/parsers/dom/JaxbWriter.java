package parsers.dom;

import model.OceanParameters;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.OutputStream;

public class JaxbWriter {

    private OutputStream outputStream;

    public JaxbWriter(OutputStream outputStream){
        this.outputStream = outputStream;

    }

    public void write(OceanParameters oceanParameters) {
        try {
            JAXBContext jc = JAXBContext.newInstance(OceanParameters.class);
            Marshaller m = jc.createMarshaller();

            m.marshal(oceanParameters, outputStream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


}

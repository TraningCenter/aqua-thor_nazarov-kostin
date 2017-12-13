package parsers.dom;

import model.OceanParameters;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

public class JaxbReader {

    public OceanParameters read(InputStream inputStream){
        try {
            JAXBContext jc = JAXBContext.newInstance(OceanParameters.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            return (OceanParameters) unmarshaller.unmarshal(inputStream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}

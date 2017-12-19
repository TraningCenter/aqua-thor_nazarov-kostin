package parsers.jaxb;

import model.parameters.OceanParameters;
import parsers.OceanParamsReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

public class JaxbOceanParamsReader implements OceanParamsReader {

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

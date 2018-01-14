package parsers.jaxb;

import dto.OceanDto;
import model.parameters.OceanParameters;
import parsers.MetricsWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.InputStream;
import java.io.OutputStream;
/**
 * writes metrics in xml using JaxB
 */
public class JaxbMetricsWriter implements MetricsWriter {
    @Override
    public void write(OceanDto oceanDto, OutputStream outputStream) {
        try {
            JAXBContext jc = JAXBContext.newInstance(OceanDto.class);
            Marshaller m = jc.createMarshaller();

            m.marshal(oceanDto, outputStream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}

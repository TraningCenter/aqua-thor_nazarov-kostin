package parsers.dom;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.OutputStream;

public class DomWriter{

    private OutputStream outputStream;

    public DomWriter(OutputStream outputStream){
        this.outputStream = outputStream;

    }

    public void write(Object obj) {
        try {
            JAXBContext jc = JAXBContext.newInstance(T.class);
            Marshaller m = jc.createMarshaller();

            m.marshal(obj, outputStream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


}

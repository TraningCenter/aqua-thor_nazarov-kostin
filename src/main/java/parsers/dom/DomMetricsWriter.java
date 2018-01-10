package parsers.dom;

import com.sun.webkit.dom.ElementImpl;
import com.sun.webkit.dom.NodeImpl;
import dto.OceanDto;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import parsers.MetricsWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class DomMetricsWriter implements MetricsWriter {
    @Override
    public void write(OceanDto oceanDto, OutputStream outputStream, InputStream inputStream) {
        try {
            createTransformer().transform(makeDom(oceanDto, inputStream), new StreamResult(outputStream));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private DOMSource makeDom(OceanDto oceanDto, InputStream inputStream) throws ParserConfigurationException, TransformerConfigurationException,IOException,SAXException {
        DocumentBuilder documentBuilder = createDocumentBuilder();
       // File file = inputStream.
       // InputStream testStream = inputStream.;
        Document document;

  /*     if (true){//(testStream.read()!=-1) {

          document = documentBuilder.parse(inputStream);
          Element stepElemant = createStep(document,oceanDto);
          Element root = (Element) document.getElementsByTagName("oceanMetrics").item(0);
          root.appendChild(stepElemant);

    } else{  */
           document = documentBuilder.newDocument();
           Element oceanMetricsElement = createOceanMetricsElement(document, oceanDto);
           document.appendChild(oceanMetricsElement);



        return new DOMSource(document);
    }

    private Element createOceanMetricsElement(Document document, OceanDto oceanDto) {
        Element oceanMetricsElement = document.createElement("oceanMetrics");

        oceanMetricsElement.appendChild(createStep(document, oceanDto));

        return oceanMetricsElement;
    }

    private Element createStep(Document document,OceanDto oceanDto) {
        Element stepElemant = document.createElement("step");
        stepElemant.appendChild(createStepCountElement(document,oceanDto));
        stepElemant.appendChild(createFishCountElement(document,oceanDto));
        stepElemant.appendChild(createSharkCountElement(document,oceanDto));

        return stepElemant;
    }

    private Element createFishCountElement(Document document, OceanDto oceanDto) {
        return createIntegerElementWithTextValue(document, "fishCount", oceanDto.getFishCount());
    }
    private Element createSharkCountElement(Document document, OceanDto oceanDto) {
        return createIntegerElementWithTextValue(document, "sharkCount", oceanDto.getSharkCount());
    }
    private Element createStepCountElement(Document document, OceanDto oceanDto) {
        return createIntegerElementWithTextValue(document, "stepCount", oceanDto.getStepCount());
    }

    private Transformer createTransformer() throws TransformerConfigurationException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        return transformer;
    }

    private Element createIntegerElementWithTextValue(Document document, String tagName, Integer value){
        Element element = document.createElement(tagName);
        element.appendChild(document.createTextNode(value.toString()));
        return element;
    }

    private DocumentBuilder createDocumentBuilder() throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        return docFactory.newDocumentBuilder();
    }
}

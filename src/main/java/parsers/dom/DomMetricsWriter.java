package parsers.dom;

import com.sun.webkit.dom.ElementImpl;
import com.sun.webkit.dom.NodeImpl;
import dto.OceanDto;
import dto.Step;
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
import java.util.List;

public class DomMetricsWriter implements MetricsWriter {
    @Override
    public void write(OceanDto oceanDto, OutputStream outputStream) {
        try {
            createTransformer().transform(makeDom(oceanDto), new StreamResult(outputStream));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private DOMSource makeDom(OceanDto oceanDto) throws ParserConfigurationException, TransformerConfigurationException,IOException,SAXException {
        DocumentBuilder documentBuilder = createDocumentBuilder();
        Document document;
           document = documentBuilder.newDocument();
           Element oceanMetricsElement = createOceanMetricsElement(document, oceanDto);
           document.appendChild(oceanMetricsElement);



        return new DOMSource(document);
    }

    private Element createOceanMetricsElement(Document document, OceanDto oceanDto) {
        Element oceanMetricsElement = document.createElement("oceanMetrics");

        oceanMetricsElement.appendChild(createOceanTypeElement(document,oceanDto));
        oceanMetricsElement.appendChild(createStepsElement(document, oceanDto));

        return oceanMetricsElement;
    }

    private Element createStepsElement(Document document,OceanDto oceanDto){
        List<Step> steps = oceanDto.getSteps();
        Element stepsElement = document.createElement("steps");

        for (Step step:steps){
            stepsElement.appendChild(createStep(document,step));
        }

        return  stepsElement;
    }

    private Element createOceanTypeElement(Document document, OceanDto oceanDto) {
        Element oceanTypeElement = document.createElement("oceanType");
        oceanTypeElement.appendChild(document.createTextNode(oceanDto.getOceanType().toString()));

        return oceanTypeElement;
    }

    private Element createStep(Document document,Step step) {
        Element stepElemant = document.createElement("step");
        stepElemant.appendChild(createStepCountElement(document,step));
        stepElemant.appendChild(createFishCountElement(document,step));
        stepElemant.appendChild(createSharkCountElement(document,step));

        return stepElemant;
    }

    private Element createFishCountElement(Document document, Step step) {
        return createIntegerElementWithTextValue(document, "fishCount", step.getFishCount());
    }
    private Element createSharkCountElement(Document document, Step step) {
        return createIntegerElementWithTextValue(document, "sharkCount", step.getSharkCount());
    }
    private Element createStepCountElement(Document document, Step step) {
        return createIntegerElementWithTextValue(document, "stepCount", step.getStepCount());
    }

    private Transformer createTransformer() throws TransformerConfigurationException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
       // transformer.setOutputProperty(OutputKeys.INDENT, "yes");
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

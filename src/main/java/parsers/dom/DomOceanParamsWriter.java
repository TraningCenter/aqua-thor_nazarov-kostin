package parsers.dom;

import model.parameters.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import parsers.OceanParamsWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;
import java.util.List;

public class DomOceanParamsWriter implements OceanParamsWriter {

    @Override
    public void write(OceanParameters oceanParameters, OutputStream outputStream) {
        try {
            createTransformer().transform(makeDom(oceanParameters), new StreamResult(outputStream));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DOMSource makeDom(OceanParameters oceanParameters) throws ParserConfigurationException, TransformerConfigurationException {
        DocumentBuilder documentBuilder = createDocumentBuilder();

        Document document = documentBuilder.newDocument();

        Element oceanParametersElement = createOceanParametersElement(document, oceanParameters);
        document.appendChild(oceanParametersElement);

        return new DOMSource(document);
    }

    private Element createOceanParametersElement(Document document, OceanParameters oceanParameters) {
        Element oceanParametersElement = document.createElement("oceanParameters");

        oceanParametersElement.appendChild(createOceanTypeElement(document, oceanParameters));
        oceanParametersElement.appendChild(createOceanSizeElement(document, oceanParameters));
        oceanParametersElement.appendChild(createFlowsElement(document,oceanParameters));
        oceanParametersElement.appendChild(createPassiveFishCountElement(document,oceanParameters));
        oceanParametersElement.appendChild(createAggressiveFishCountElement(document,oceanParameters));
        oceanParametersElement.appendChild(createPassiveFishParameters(document,oceanParameters));
        oceanParametersElement.appendChild(createAggressiveFishParameters(document,oceanParameters));

        return oceanParametersElement;
    }

    private Element createAggressiveFishParameters(Document document, OceanParameters oceanParameters) {
        return createFishParametersElement(document, "aggressiveFishParameters", oceanParameters.getAggressiveFishParameters());
    }

    private Element createPassiveFishParameters(Document document, OceanParameters oceanParameters) {
        return createFishParametersElement(document, "passiveFishParameters", oceanParameters.getPassiveFishParameters());
    }

    private Element createAggressiveFishCountElement(Document document, OceanParameters oceanParameters) {
        return createIntegerElementWithTextValue(document, "aggressiveFishCount", oceanParameters.getAggressiveFishCount());
    }

    private Element createPassiveFishCountElement(Document document, OceanParameters oceanParameters) {
        return createIntegerElementWithTextValue(document, "passiveFishCount", oceanParameters.getPassiveFishCount());
    }

    private Element createOceanSizeElement(Document document, OceanParameters oceanParameters) {
        return createVectorElement(document, "oceanSize", oceanParameters.getOceanSize());
    }

    private Element createFlowsElement(Document document,OceanParameters oceanParameters){
        List<Flow> flows = oceanParameters.getFlows();
        Element flowsElement = document.createElement("flows");
        for (Flow flow:flows){
            flowsElement.appendChild(createFlowElement(document,flow));
        }
        return flowsElement;
    }

    private Element createFlowElement(Document document, Flow flow) {

        Element flowElement = document.createElement("flow");
        flowElement.appendChild(createVectorElement(document, "direction", flow.getDirection()));
        flowElement.appendChild(createRectangle(document,flow));
        flowElement.appendChild(createIntegerElementWithTextValue(document, "strength", flow.getStrength()));

        return flowElement;
    }

    private Element createRectangle(Document document,Flow flow){
        Rectangle rectangle = flow.getRectangle();

        Element rectangleElement = document.createElement("rectangle");

        rectangleElement.appendChild(createIntegerElementWithTextValue(document,"x",rectangle.getX()));
        rectangleElement.appendChild(createIntegerElementWithTextValue(document,"y",rectangle.getY()));
        rectangleElement.appendChild(createIntegerElementWithTextValue(document,"width",rectangle.getWidth()));
        rectangleElement.appendChild(createIntegerElementWithTextValue(document,"height",rectangle.getHeight()));

        return rectangleElement;
    }

    private Element createOceanTypeElement(Document document, OceanParameters oceanParameters) {
        Element oceanTypeElement = document.createElement("oceanType");
        oceanTypeElement.appendChild(document.createTextNode(oceanParameters.getOceanType().toString()));

        return oceanTypeElement;
    }

    private Element createFishParametersElement(Document document, String tagName, FishParameters fishParameters){
        Element fishParametersElement = document.createElement(tagName);

        fishParametersElement.appendChild(createIntegerElementWithTextValue(document,"lifeTimeTicks",fishParameters.getLifeTimeTicks()));
        fishParametersElement.appendChild(createIntegerElementWithTextValue(document,"reproductionPeriodTicks",fishParameters.getReproductionPeriodTicks()));
        fishParametersElement.appendChild(createIntegerElementWithTextValue(document,"smellSenseDistance",fishParameters.getSmellSenseDistance()));
        fishParametersElement.appendChild(createIntegerElementWithTextValue(document,"starvationTimeTicks",fishParameters.getStarvationTimeTicks()));
        fishParametersElement.appendChild(createIntegerElementWithTextValue(document,"timeToMoveThroughOneCell",fishParameters.getTimeToMoveThroughOneCell()));

        return fishParametersElement;
    }

    private Element createVectorElement(Document document, String tagName, Vector vector){
        Element vectorElement = document.createElement(tagName);

        vectorElement.appendChild(createIntegerElementWithTextValue(document,"x",vector.getX()));
        vectorElement.appendChild(createIntegerElementWithTextValue(document,"y",vector.getY()));

        return vectorElement;
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

    private Transformer createTransformer() throws TransformerConfigurationException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        return transformer;
    }
}

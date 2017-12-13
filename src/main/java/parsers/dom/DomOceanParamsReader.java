package parsers.dom;

import jdk.nashorn.internal.runtime.ParserException;
import model.*;
import org.w3c.dom.*;
import parsers.OceanParamsReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.InputStream;

public class DomOceanParamsReader implements OceanParamsReader {

    private OceanParameters oceanParameters;

    @Override
    public OceanParameters read(InputStream inputStream) {
        try {
            startParse(createDocumentBuilder().parse(inputStream));

            return oceanParameters;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private DocumentBuilder createDocumentBuilder() throws ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        return dbFactory.newDocumentBuilder();
    }

    private void startParse(Document document) {
        oceanParameters = new OceanParameters();
        Element documentElement = document.getDocumentElement();

        parseOceanType(getFirstElementByTagName(documentElement,"oceanType"));
        parseOceanSize(getFirstElementByTagName(documentElement,"oceanSize"));
        parseOceanFlow(getFirstElementByTagName(documentElement,"flow"));
        parsePassiveFishCount(getFirstElementByTagName(documentElement,"passiveFishCount"));
        parseAggressiveFishCount(getFirstElementByTagName(documentElement,"aggressiveFishCount"));
        parsePassiveFishParameters(getFirstElementByTagName(documentElement,"passiveFishParameters"));
        parseAggressiveFishParameters(getFirstElementByTagName(documentElement,"aggressiveFishParameters"));

    }

    private void parsePassiveFishParameters(Element passiveFishParameters) {
        oceanParameters.setPassiveFishParameters(parseFishParameters(passiveFishParameters));
    }

    private void parseAggressiveFishParameters(Element aggressiveFishParameters) {
        oceanParameters.setAggressiveFishParameters(parseFishParameters(aggressiveFishParameters));
    }

    private void parseAggressiveFishCount(Element aggressiveFishCountElement) {
        oceanParameters.setAggressiveFishCount(parseInteger(aggressiveFishCountElement));
    }

    private void parsePassiveFishCount(Element passiveFishCountElement) {
        oceanParameters.setPassiveFishCount(parseInteger(passiveFishCountElement));
    }

    private void parseOceanFlow(Element flowElement) {
        Flow flow = new Flow();

        flow.setDirection(parseVector(getFirstElementByTagName(flowElement, "direction")));
        flow.setStrength(parseInteger(getFirstElementByTagName(flowElement, "strength")));

        oceanParameters.setFlow(flow);
    }

    private void parseOceanSize(Element oceanSizeElement) {
        oceanParameters.setOceanSize(parseVector(oceanSizeElement));
    }

    private void parseOceanType(Node oceanTypeNode) {
        switch (oceanTypeNode.getTextContent()){
            case "BORDERED":
                oceanParameters.setOceanType(OceanType.BORDERED);
                break;
            case "BORDERLESS":
                oceanParameters.setOceanType(OceanType.BORDERLESS);
                break;
        }
    }

    private FishParameters parseFishParameters(Element fishParametersElement){
        FishParameters fishParameters = new FishParameters();

        fishParameters.setLifeTimeTicks(parseIntegerAtTag(fishParametersElement,"lifeTimeTicks"));
        fishParameters.setReproductionPeriodTicks(parseIntegerAtTag(fishParametersElement,"reproductionPeriodTicks"));
        fishParameters.setSmellSenseDistance(parseIntegerAtTag(fishParametersElement,"smellSenseDistance"));
        fishParameters.setStarvationTimeTicks(parseIntegerAtTag(fishParametersElement,"starvationTimeTicks"));

        return fishParameters;
    }

    private Vector parseVector(Element vectorElement){
        Vector vector = new Vector();

        vector.setX(parseIntegerAtTag(vectorElement, "x"));
        vector.setY(parseIntegerAtTag(vectorElement, "y"));

        return vector;
    }

    private Integer parseIntegerAtTag(Element element, String tagName){
        return parseInteger(getFirstElementByTagName(element,tagName));
    }

    private Integer parseInteger(Node integerNode){
        return Integer.parseInt(integerNode.getTextContent());
    }

    private Element getFirstElementByTagName(Element elementToSearch, String tagName){
        return (Element)elementToSearch.getElementsByTagName(tagName).item(0);
    }
}

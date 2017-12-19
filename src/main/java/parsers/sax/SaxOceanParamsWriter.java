package parsers.sax;

import model.parameters.OceanParameters;
import org.xml.sax.SAXException;
import parsers.OceanParamsWriter;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;

public class SaxOceanParamsWriter implements OceanParamsWriter {

    private TransformerFactory factory;
    private SAXTransformerFactory saxTransFactory;
    private TransformerHandler transHandler;
    private OceanParameters oceanParameters;

    public SaxOceanParamsWriter() {
        try {
            factory = TransformerFactory.newInstance().newInstance();
            saxTransFactory = (SAXTransformerFactory) factory;
            transHandler = saxTransFactory.newTransformerHandler();

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void write(OceanParameters oceanParameters, OutputStream outputStream) {
        try {
            this.oceanParameters = oceanParameters;
            transHandler.setResult(new StreamResult(outputStream));
            transHandler.startDocument();
            writeRoot();
            transHandler.endDocument();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    private void writeRoot() throws SAXException {
        transHandler.startElement("", "", "oceanParameters", null);
        writeOceanType();
        writeOceanSize();
        writeFlow();
        writePasCount();
        writeAgrCount();
        writePasParam();
        writeAgrParam();
        transHandler.endElement("", "", "oceanParameters");
    }

    private void writeOceanType() throws SAXException {
        transHandler.startElement("", "", "oceanType", null);
        char[] temp = oceanParameters.getOceanType().toString().toCharArray();
        transHandler.characters(temp, 0, temp.length);
        transHandler.endElement("", "", "oceanType");
    }

    private void writeOceanSize() throws SAXException {
        transHandler.startElement("", "", "oceanSize", null);
        writeSizeX();
        writeSizeY();
        transHandler.endElement("", "", "oceanSize");
    }

    private void writeSizeX() throws SAXException {
        transHandler.startElement("", "", "x", null);
        char[] temp = oceanParameters.getOceanSize().getX().toString().toCharArray();
        transHandler.characters(temp, 0, temp.length);
        transHandler.endElement("", "", "x");
    }

    private void writeSizeY() throws SAXException {
        transHandler.startElement("", "", "y", null);
        char[] temp = oceanParameters.getOceanSize().getY().toString().toCharArray();
        transHandler.characters(temp, 0, temp.length);
        transHandler.endElement("", "", "y");
    }

    private void writeFlow() throws SAXException {
        transHandler.startElement("", "", "flow", null);
        writeDirection();
        writeStrength();
        transHandler.endElement("", "", "flow");
    }

    private void writeDirection() throws SAXException {
        transHandler.startElement("", "", "direction", null);
        writeDirectionX();
        writeDirectionY();
        transHandler.endElement("", "", "direction");
    }

    private void writeDirectionX() throws SAXException {
        transHandler.startElement("", "", "x", null);
        char[] temp = oceanParameters.getFlow().getDirection().getX().toString().toCharArray();
        transHandler.characters(temp, 0, temp.length);
        transHandler.endElement("", "", "x");
    }

    private void writeDirectionY() throws SAXException {
        transHandler.startElement("", "", "y", null);
        char[] temp = oceanParameters.getFlow().getDirection().getY().toString().toCharArray();
        transHandler.characters(temp, 0, temp.length);
        transHandler.endElement("", "", "y");
    }

    private void writeStrength() throws SAXException {
        transHandler.startElement("", "", "strength", null);
        char[] temp = oceanParameters.getFlow().getStrength().toString().toCharArray();
        transHandler.characters(temp, 0, temp.length);
        transHandler.endElement("", "", "strength");
    }

    private void writeAgrCount() throws SAXException {
        transHandler.startElement("", "", "aggressiveFishCount", null);
        char[] temp = oceanParameters.getAggressiveFishCount().toString().toCharArray();
        transHandler.characters(temp, 0, temp.length);
        transHandler.endElement("", "", "aggressiveFishCount");
    }

    private void writePasCount() throws SAXException {
        transHandler.startElement("", "", "passiveFishCount", null);
        char[] temp = oceanParameters.getPassiveFishCount().toString().toCharArray();
        transHandler.characters(temp, 0, temp.length);
        transHandler.endElement("", "", "passiveFishCount");
    }

    private void writePasParam() throws SAXException {
        transHandler.startElement("", "", "passiveFishParameters", null);
        writeLifeTime(true);
        writeReproductionPeriod(true);
        writeSmellSenseDistance(true);
        writeStarvationTime(true);
        transHandler.endElement("", "", "passiveFishParameters");
    }

    private void writeAgrParam() throws SAXException {
        transHandler.startElement("", "", "aggressiveFishParameters", null);
        writeLifeTime(false);
        writeReproductionPeriod(false);
        writeSmellSenseDistance(false);
        writeStarvationTime(false);
        transHandler.endElement("", "", "aggressiveFishParameters");
    }

    private void writeLifeTime(boolean isPassive) throws SAXException {
        char[] temp;
        if (isPassive) {
            temp = oceanParameters.getPassiveFishParameters().getLifeTimeTicks().toString().toCharArray();
        } else {
            temp = oceanParameters.getAggressiveFishParameters().getLifeTimeTicks().toString().toCharArray();
        }
        transHandler.startElement("", "", "lifeTimeTicks", null);
        transHandler.characters(temp, 0, temp.length);
        transHandler.endElement("", "", "lifeTimeTicks");
    }

    private void writeReproductionPeriod(boolean isPassive) throws SAXException {
        char[] temp;
        if (isPassive) {
            temp = oceanParameters.getPassiveFishParameters().getReproductionPeriodTicks().toString().toCharArray();
        } else {
            temp = oceanParameters.getAggressiveFishParameters().getReproductionPeriodTicks().toString().toCharArray();
        }
        transHandler.startElement("", "", "reproductionPeriodTicks", null);
        transHandler.characters(temp, 0, temp.length);
        transHandler.endElement("", "", "reproductionPeriodTicks");
    }

    private void writeSmellSenseDistance(boolean isPassive) throws SAXException {
        char[] temp;
        if (isPassive) {
            temp = oceanParameters.getPassiveFishParameters().getSmellSenseDistance().toString().toCharArray();
        } else {
            temp = oceanParameters.getAggressiveFishParameters().getSmellSenseDistance().toString().toCharArray();
        }
        transHandler.startElement("", "", "smellSenseDistance", null);
        transHandler.characters(temp, 0, temp.length);
        transHandler.endElement("", "", "smellSenseDistance");
    }

    private void writeStarvationTime(boolean isPassive) throws SAXException {
        char[] temp;
        if (isPassive) {
            temp = oceanParameters.getPassiveFishParameters().getStarvationTimeTicks().toString().toCharArray();
        } else {
            temp = oceanParameters.getAggressiveFishParameters().getStarvationTimeTicks().toString().toCharArray();
        }
        transHandler.startElement("", "", "starvationTimeTicks", null);
        transHandler.characters(temp, 0, temp.length);
        transHandler.endElement("", "", "starvationTimeTicks");
    }
}

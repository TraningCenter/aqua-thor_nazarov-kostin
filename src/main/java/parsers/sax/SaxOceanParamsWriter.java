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
        writeFlows();
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

    private void writeFlows() throws SAXException {
        transHandler.startElement("", "", "flows", null);
        for (int i=0;i<oceanParameters.getFlows().size();i++){
         writeFlow(i);
        }
        transHandler.endElement("", "", "flows");
    }
    private void writeFlow(int number) throws SAXException {
        transHandler.startElement("", "", "flow", null);
        writeDirection(number);
        writeRectangle(number);
        writeStrength(number);
        transHandler.endElement("", "", "flow");
    }

    private void writeRectangle(int number) throws SAXException{
        transHandler.startElement("","","rectangle",null);
        writeRecX(number);
        writeRecY(number);
        writeRecWidth(number);
        writeRecHeight(number);
        transHandler.endElement("","","rectangle");
    }

    private void writeRecX(int number) throws SAXException{
        transHandler.startElement("","","x",null);
        char[] temp=oceanParameters.getFlows().get(number).getRectangle().getX().toString().toCharArray();
        transHandler.characters(temp,0,temp.length);
        transHandler.endElement("","","x");
    }

    private void writeRecY(int number) throws SAXException{
        transHandler.startElement("","","y",null);
        char[] temp=oceanParameters.getFlows().get(number).getRectangle().getY().toString().toCharArray();
        transHandler.characters(temp,0,temp.length);
        transHandler.endElement("","","y");
    }

    private void writeRecWidth(int number) throws SAXException{
        transHandler.startElement("","","width",null);
        char[] temp=oceanParameters.getFlows().get(number).getRectangle().getWidth().toString().toCharArray();
        transHandler.characters(temp,0,temp.length);
        transHandler.endElement("","","width");
    }
    private void writeRecHeight(int number) throws SAXException{
        transHandler.startElement("","","height",null);
        char[] temp=oceanParameters.getFlows().get(number).getRectangle().getHeight().toString().toCharArray();
        transHandler.characters(temp,0,temp.length);
        transHandler.endElement("","","height");
    }

    private void writeDirection(int number) throws SAXException {
        transHandler.startElement("", "", "direction", null);
        writeDirectionX(number);
        writeDirectionY(number);
        transHandler.endElement("", "", "direction");
    }

    private void writeDirectionX(int number) throws SAXException {
        transHandler.startElement("", "", "x", null);
        char[] temp = oceanParameters.getFlows().get(number).getDirection().getX().toString().toCharArray();
        transHandler.characters(temp, 0, temp.length);
        transHandler.endElement("", "", "x");
    }

    private void writeDirectionY(int number) throws SAXException {
        transHandler.startElement("", "", "y", null);
        char[] temp = oceanParameters.getFlows().get(number).getDirection().getY().toString().toCharArray();
        transHandler.characters(temp, 0, temp.length);
        transHandler.endElement("", "", "y");
    }

    private void writeStrength(int number) throws SAXException {
        transHandler.startElement("", "", "strength", null);
        char[] temp = oceanParameters.getFlows().get(number).getStrength().toString().toCharArray();
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
        writeTimeToMoveThroughOneCell(true);
        transHandler.endElement("", "", "passiveFishParameters");
    }

    private void writeAgrParam() throws SAXException {
        transHandler.startElement("", "", "aggressiveFishParameters", null);
        writeLifeTime(false);
        writeReproductionPeriod(false);
        writeSmellSenseDistance(false);
        writeStarvationTime(false);
        writeTimeToMoveThroughOneCell(false);
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

    private void writeTimeToMoveThroughOneCell(boolean isPassive)throws SAXException{
        char[] temp;
        if (isPassive) {
            temp = oceanParameters.getPassiveFishParameters().getTimeToMoveThroughOneCell().toString().toCharArray();
        }else{
            temp=oceanParameters.getAggressiveFishParameters().getTimeToMoveThroughOneCell().toString().toCharArray();
        }
        transHandler.startElement("","","timeToMoveThroughOneCell",null);
        transHandler.characters(temp,0,temp.length);
        transHandler.endElement("","","timeToMoveThroughOneCell");
    }
}

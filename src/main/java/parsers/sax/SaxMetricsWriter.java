package parsers.sax;

import dto.OceanDto;
import dto.Step;
import org.xml.sax.SAXException;
import parsers.MetricsWriter;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.InputStream;
import java.io.OutputStream;
/**
 * writes metrics in xml using SAX
 */
public class SaxMetricsWriter implements MetricsWriter {


    private TransformerFactory factory;
    private SAXTransformerFactory saxTransFactory;
    private TransformerHandler transHandler;
    private OceanDto oceanDto;

    public SaxMetricsWriter() {
        try {
            factory = TransformerFactory.newInstance().newInstance();
            saxTransFactory = (SAXTransformerFactory) factory;
            transHandler = saxTransFactory.newTransformerHandler();

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(OceanDto oceanDto, OutputStream outputStream) {
        try {
            this.oceanDto=oceanDto;
            transHandler.setResult(new StreamResult(outputStream));
            transHandler.startDocument();
            writeRoot();
            transHandler.endDocument();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    private void writeRoot() throws SAXException {
        transHandler.startElement("", "", "oceanMetrics", null);
        writeOceanType();
        writeSteps();
        transHandler.endElement("", "", "oceanMetrics");
    }

    private void writeSteps() throws SAXException{
        transHandler.startElement("","","steps",null);
        for (Step step: oceanDto.getSteps()){
            writeStep(step);
        }
        transHandler.endElement("","","steps");
    }

    private void writeStep(Step step) throws SAXException{
        transHandler.startElement("","","step",null);
        writeStepNumber(step);
        writeFishCount(step);
        writeSharkCount(step);
        transHandler.endElement("","","step");
    }

    private void writeOceanType()throws  SAXException{
        transHandler.startElement("","","oceanType",null);
        char[] temp = oceanDto.getOceanType().toString().toCharArray();
        transHandler.characters(temp, 0, temp.length);
        transHandler.endElement("", "", "oceanType");
    }

    private void writeStepNumber(Step step)throws  SAXException{
        transHandler.startElement("","","stepCount",null);
        char[] temp = Integer.toString(step.getStepCount()).toCharArray();
        transHandler.characters(temp, 0, temp.length);
        transHandler.endElement("", "", "stepCount");
    }

    private void writeFishCount(Step step)throws  SAXException{
        transHandler.startElement("","","fishCount",null);
        char[] temp = Integer.toString(step.getFishCount()).toCharArray();
        transHandler.characters(temp, 0, temp.length);
        transHandler.endElement("", "", "fishCount");
    }

    private void writeSharkCount(Step step)throws  SAXException{
        transHandler.startElement("","","sharkCount",null);
        char[] temp = Integer.toString(step.getSharkCount()).toCharArray();
        transHandler.characters(temp, 0, temp.length);
        transHandler.endElement("", "", "sharkCount");
    }
}

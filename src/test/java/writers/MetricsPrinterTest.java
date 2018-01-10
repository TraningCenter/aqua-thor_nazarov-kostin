package writers;

import configurator.implementation.DefaultParserChanger;
import configurator.interfaces.ParserChanger;
import dto.OceanDto;
import jdk.internal.util.xml.impl.Input;
import model.parameters.OceanType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import writers.implementation.XmlMetricsPrinter;
import writers.interfaces.MetricsPrinter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetricsPrinterTest {

    OceanDto oceanDto;
    MetricsPrinter printer;

    @Before
    public void init() {

        printer = new XmlMetricsPrinter();

        printer.setPath("src/test/resources/OceanMetrics.xml");

        oceanDto = new OceanDto() {
            {
                setOceanType(OceanType.BORDERED);
                setStepCount(5);
                setFishCount(10);
                setSharkCount(7);
            }
        };
    }

    @Test
    public void testSax() {

        ParserChanger parserChanger = new DefaultParserChanger(){

            @Override
            protected Map<String,String> readPropertiesFile() throws IOException{

                return new HashMap<String,String>(){
                    {
                        put("MetricsWriter","SAX");
                    }
                };
            }
        };

        parse(parserChanger);

    }

   @Test
    public void testJaxb() {

        ParserChanger parserChanger = new DefaultParserChanger(){

            @Override
            protected Map<String,String> readPropertiesFile() throws IOException{


                return new HashMap<String,String>(){
                    {
                        put("MetricsWriter","JAXB");
                    }
                };
            }
        };

        parse(parserChanger);

    }

    @Test
    public void testDom() {

        ParserChanger parserChanger = new DefaultParserChanger(){

            @Override
            protected Map<String,String> readPropertiesFile() throws IOException{


                return new HashMap<String,String>(){
                    {
                        put("MetricsWriter","DOM");
                    }
                };
            }
        };

        parse(parserChanger);

    }

    private void parse(ParserChanger parserChanger){
        try (FileInputStream fis = new FileInputStream("src/test/resources/OceanMetrics.xml")){
            printer.writeMetrics(oceanDto,parserChanger);
            boolean starter = false;
            StringBuffer sb = new StringBuffer();
            int i=-1;
            while((i=fis.read())!=-1){
                if (starter){
                    sb.append((char)i);
                }
                if ((char)i=='>'){
                    starter=true;
                }
            }
            String expected="<oceanMetrics><oceanType>BORDERED</oceanType><stepCount>5</stepCount><fishCount>10</fishCount><sharkCount>7</sharkCount></oceanMetrics>";
            Assert.assertEquals(expected,sb.toString());
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

}

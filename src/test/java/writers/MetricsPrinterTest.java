package writers;

import configurator.implementation.DefaultParserChanger;
import configurator.implementation.XmlOceanCreator;
import configurator.interfaces.ParserChanger;
import dto.OceanDto;
import dto.Step;
import dto.translators.OceanTranslator;
import model.ocean.interfaces.Ocean;
import model.parameters.OceanType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parameters.OceanParametersMock;
import readers.XmlParamsReaderMock;
import writers.implementation.XmlMetricsPrinter;
import writers.interfaces.MetricsPrinter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetricsPrinterTest {

    OceanDto oceanDto;
    MetricsPrinter printer;

    @Before
    public void init() {





        oceanDto = new OceanDto() {
            {
                XmlOceanCreator creator = new XmlOceanCreator();
               Ocean ocean = creator.createOcean(null,new XmlParamsReaderMock());
               OceanParametersMock params = new OceanParametersMock();
                OceanTranslator translator = new OceanTranslator();
                OceanDto oceanDto = translator.translateToDto(this,ocean);

                setOceanType(OceanType.BORDERED);
                List<Step> steps =  new ArrayList<Step>(){
                    {
                        add(new Step(1,oceanDto.getFishes()));
                        add(new Step(2,oceanDto.getFishes()));
                    }
                };
                setSteps(steps);
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
        printer = new XmlMetricsPrinter(parserChanger);
        printer.setPath("src/test/resources/OceanMetrics.xml");
        try (FileInputStream fis = new FileInputStream("src/test/resources/OceanMetrics.xml")){
            printer.writeMetrics(oceanDto);
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
            String expected="<oceanMetrics><oceanType>BORDERED</oceanType><steps><step><stepCount>1</stepCount><fishCount>15</fishCount><sharkCount>10</sharkCount></step><step><stepCount>2</stepCount><fishCount>15</fishCount><sharkCount>10</sharkCount></step></steps></oceanMetrics>";
            Assert.assertEquals(expected,sb.toString());
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

}

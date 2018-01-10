package writers;

import configurator.implementation.DefaultParserChanger;
import configurator.interfaces.ParserChanger;
import dto.OceanDto;
import model.parameters.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parameters.OceanParametersMock;
import writers.implementation.XmlMetricsPrinter;
import writers.implementation.XmlParamsPrinter;
import writers.interfaces.ParamsPrinter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParamsPrinterTest {
    OceanParameters oceanParameters;
    ParamsPrinter printer;

    @Before
    public void init() {

        printer = new XmlParamsPrinter();

        printer.setPath("src/test/resources/OceanParams.xml");

         oceanParameters = new OceanParametersMock().getParameters();
    }

    @Test
    public void testSax() {

        ParserChanger parserChanger = new DefaultParserChanger(){

            @Override
            protected Map<String,String> readPropertiesFile() throws IOException {


                return new HashMap<String,String>(){
                    {
                        put("OceanParamsWriter","SAX");
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
                        put("OceanParamsWriter","JAXB");
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
                        put("OceanParamsWriter","DOM");
                    }
                };
            }
        };

        parse(parserChanger);

    }

    private void parse(ParserChanger parserChanger){
        try (FileInputStream fis = new FileInputStream("src/test/resources/OceanParams.xml")){
            printer.writeParams(oceanParameters,parserChanger);
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
            String expected = "<oceanParameters><oceanType>BORDERED</oceanType><oceanSize><x>100</x><y>100</y></oceanSize><flows><flow><direction><x>4</x><y>5</y></direction><rectangle><x>10</x><y>30</y><width>50</width><height>20</height></rectangle><strength>3</strength></flow><flow><direction><x>5</x><y>9</y></direction><rectangle><x>20</x><y>5</y><width>30</width><height>40</height></rectangle><strength>1</strength></flow></flows><passiveFishCount>15</passiveFishCount><aggressiveFishCount>10</aggressiveFishCount><passiveFishParameters><lifeTimeTicks>10</lifeTimeTicks><reproductionPeriodTicks>3</reproductionPeriodTicks><smellSenseDistance>10</smellSenseDistance><starvationTimeTicks>4</starvationTimeTicks><timeToMoveThroughOneCell>5</timeToMoveThroughOneCell></passiveFishParameters><aggressiveFishParameters><lifeTimeTicks>9</lifeTimeTicks><reproductionPeriodTicks>2</reproductionPeriodTicks><smellSenseDistance>7</smellSenseDistance><starvationTimeTicks>1</starvationTimeTicks><timeToMoveThroughOneCell>7</timeToMoveThroughOneCell></aggressiveFishParameters></oceanParameters>";

                    Assert.assertEquals(expected,sb.toString());
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }


}

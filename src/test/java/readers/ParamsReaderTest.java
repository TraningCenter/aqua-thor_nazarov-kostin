package readers;

import configurator.implementation.DefaultParserChanger;
import configurator.interfaces.ParserChanger;
import model.parameters.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parameters.OceanParametersMock;
import readers.implementation.XmlParametersReader;
import parsers.ParametersReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ParamsReaderTest {

    OceanParameters expectedOceanParameters;
    ParametersReader reader;


    @Before
    public void init() {

        reader = new XmlParametersReader();
        reader.setPath("src/test/resources/OceanParams.xml");
        expectedOceanParameters = new OceanParametersMock().getParameters();

    }

    @Test
    public void testSax() {

        ParserChanger parserChanger = new DefaultParserChanger(){

            @Override
            protected Map<String,String> readPropertiesFile() throws IOException {


                return new HashMap<String,String>(){
                    {
                        put("OceanParamsReader","SAX");
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
                        put("OceanParamsReader","JAXB");
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
                        put("OceanParamsReader","DOM");
                    }
                };
            }
        };

        parse(parserChanger);

    }

    private void parse(ParserChanger parserChanger){

         OceanParameters  oceanParameters =  reader.readParams(parserChanger);


            Assert.assertEquals(expectedOceanParameters,oceanParameters);

    }



}

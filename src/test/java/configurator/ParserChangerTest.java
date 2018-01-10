package configurator;

import configurator.implementation.DefaultParserChanger;
import configurator.interfaces.ParserChanger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parsers.dom.DomMetricsWriter;
import parsers.jaxb.JaxbMetricsWriter;
import parsers.sax.SaxMetricsWriter;
import parsers.sax.SaxOceanParamsReader;
import parsers.sax.SaxOceanParamsWriter;

import java.io.IOException;

public class ParserChangerTest {

    ParserChanger changer;

    @Before
    public void init(){
         changer = new DefaultParserChanger(){
             {
                 setPath("src/test/resources/parserType.properties");
             }
         };

    }

    @Test
    public void testMetricsReader(){
        try {
            Assert.assertTrue( changer.changeMetricsWriter().getClass()== JaxbMetricsWriter.class);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
    @Test
    public void testParamsReader(){
        try {
            Assert.assertTrue( changer.changeReader().getClass()== SaxOceanParamsReader.class);

        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
    @Test
    public void testParamsWriter(){
        try {
            Assert.assertTrue( changer.changeWriter().getClass()== SaxOceanParamsWriter.class);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
}

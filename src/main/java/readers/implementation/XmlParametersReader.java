package readers.implementation;

import configurator.interfaces.ParserChanger;
import model.parameters.OceanParameters;
import parsers.ParametersReader;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * reads parameters from xml
 */
public class XmlParametersReader implements ParametersReader{

    private String path = "OceanParams.xml";

    @Override
    public OceanParameters readParams(ParserChanger parserChanger)  {


        try (FileInputStream fin = new FileInputStream(path)) {
            return parserChanger.changeReader().read(fin);
        }
        catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
    }

    public void setPath(String path) {
        this.path = path;
    }
}

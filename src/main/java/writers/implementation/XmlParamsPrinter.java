package writers.implementation;

import configurator.implementation.DefaultParserChanger;
import configurator.interfaces.ParserChanger;
import model.parameters.OceanParameters;
import parsers.OceanParamsWriter;
import writers.interfaces.ParamsPrinter;

import java.io.FileOutputStream;
import java.io.IOException;

public class XmlParamsPrinter implements ParamsPrinter {


    private String path= "OceanParams.xml";


    @Override
    public void writeParams(OceanParameters oceanParameters, ParserChanger parserChanger) {
        OceanParamsWriter oceanParamsWriter;


        try (FileOutputStream fos = new FileOutputStream(path)) {
            oceanParamsWriter = parserChanger.changeWriter();
            oceanParamsWriter.write(oceanParameters,fos);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setPath(String path) {
        this.path = path;
    }
}

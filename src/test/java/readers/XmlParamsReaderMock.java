package readers;

import configurator.interfaces.ParserChanger;
import model.parameters.OceanParameters;
import parameters.OceanParametersMock;
import readers.implementation.XmlParametersReader;

import java.io.FileInputStream;
import java.io.IOException;

public class XmlParamsReaderMock extends XmlParametersReader {

    @Override
    public OceanParameters readParams(ParserChanger parserChanger)  {

        return new OceanParametersMock().getParameters();

    }
}

package configurator.interfaces;

import model.ocean.interfaces.Ocean;
import readers.implementation.XmlParametersReader;
import readers.interfaces.ParametersReader;

import java.io.IOException;

public interface OceanCreator {

    public Ocean createOcean(ParserChanger parserChanger, XmlParametersReader reader);
}

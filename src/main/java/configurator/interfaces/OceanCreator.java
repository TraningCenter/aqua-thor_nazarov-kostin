package configurator.interfaces;

import model.ocean.interfaces.Ocean;
import readers.implementation.XmlParametersReader;

public interface OceanCreator {

    public Ocean createOcean(ParserChanger parserChanger, XmlParametersReader reader);
}

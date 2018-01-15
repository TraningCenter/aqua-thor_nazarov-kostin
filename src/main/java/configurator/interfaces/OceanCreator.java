package configurator.interfaces;

import model.ocean.interfaces.Ocean;
import readers.implementation.XmlParametersReader;

/**
 * interface for classes which can create oceans with necessary parameters
 */
public interface OceanCreator {

    public Ocean createOcean(ParserChanger parserChanger, XmlParametersReader reader);
}

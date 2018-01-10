package configurator.interfaces;

import model.ocean.interfaces.Ocean;

import java.io.IOException;

public interface OceanCreator {

    public Ocean createOcean(ParserChanger parserChanger);
}

package parsers;

import configurator.interfaces.ParserChanger;
import model.parameters.OceanParameters;

/**
 * reads metrics
 */
public interface ParametersReader {

    public OceanParameters readParams(ParserChanger parserChanger);

    public void setPath(String path);
}

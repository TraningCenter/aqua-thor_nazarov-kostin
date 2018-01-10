package readers.interfaces;

import configurator.interfaces.ParserChanger;
import model.parameters.OceanParameters;

public interface ParametersReader {

    public OceanParameters readParams(ParserChanger parserChanger);

    public void setPath(String path);
}

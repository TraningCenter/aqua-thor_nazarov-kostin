package writers.interfaces;

import configurator.interfaces.ParserChanger;
import dto.OceanDto;
import model.parameters.OceanParameters;

public interface ParamsPrinter {

    public void writeParams(OceanParameters oceanParameters, ParserChanger parserChanger);

    public void setPath(String path);
}

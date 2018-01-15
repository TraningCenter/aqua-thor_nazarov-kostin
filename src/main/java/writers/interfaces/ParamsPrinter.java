package writers.interfaces;

import configurator.interfaces.ParserChanger;
import dto.OceanDto;
import model.parameters.OceanParameters;
/**
 * Picks xml parser using .properties file  and writes parameters in file
 */
public interface ParamsPrinter {

    public void writeParams(OceanParameters oceanParameters, ParserChanger parserChanger);

    public void setPath(String path);
}

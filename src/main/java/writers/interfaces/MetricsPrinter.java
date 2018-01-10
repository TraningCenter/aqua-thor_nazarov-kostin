package writers.interfaces;

import configurator.interfaces.ParserChanger;
import dto.OceanDto;

public interface MetricsPrinter {

    public void writeMetrics(OceanDto oceanDto, ParserChanger parserChanger);

    public void setPath(String path);
}

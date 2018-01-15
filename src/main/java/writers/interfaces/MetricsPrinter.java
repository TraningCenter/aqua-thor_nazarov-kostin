package writers.interfaces;

import configurator.interfaces.ParserChanger;
import dto.OceanDto;
/**
 * Picks xml parser using .properties file  and writes metrics in file
 */
public interface MetricsPrinter {

    public void writeMetrics(OceanDto oceanDto);

    public void setPath(String path);
}

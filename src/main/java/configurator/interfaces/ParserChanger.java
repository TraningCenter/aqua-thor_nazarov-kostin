package configurator.interfaces;

import parsers.MetricsWriter;
import parsers.OceanParamsReader;
import parsers.OceanParamsWriter;

import java.io.IOException;

/**
 * interface for classes, which can change xml parsers
 */
public interface ParserChanger {
    public OceanParamsReader changeReader() throws IOException ;
    public OceanParamsWriter changeWriter() throws IOException ;
    public MetricsWriter changeMetricsWriter() throws IOException ;
}

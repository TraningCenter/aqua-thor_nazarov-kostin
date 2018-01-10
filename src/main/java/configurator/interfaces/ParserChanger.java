package configurator.interfaces;

import parsers.MetricsWriter;
import parsers.OceanParamsReader;
import parsers.OceanParamsWriter;

import java.io.IOException;

public interface ParserChanger {
    public OceanParamsReader changeReader() throws IOException ;
    public OceanParamsWriter changeWriter() throws IOException ;
    public MetricsWriter changeMetricsWriter() throws IOException ;
}

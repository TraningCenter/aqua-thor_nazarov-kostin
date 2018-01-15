package parsers;

import dto.OceanDto;

import java.io.InputStream;
import java.io.OutputStream;
/**
 * writes metrics in file
 */
public interface MetricsWriter {

    public void write(OceanDto oceanDto, OutputStream outputStream);
}

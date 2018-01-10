package parsers;

import dto.OceanDto;

import java.io.InputStream;
import java.io.OutputStream;

public interface MetricsWriter {

    public void write(OceanDto oceanDto, OutputStream outputStream, InputStream inputStream);
}

package parsers;

import model.OceanParameters;

import java.io.OutputStream;

public interface OceanParamsWriter {
    void write(OceanParameters oceanParameters, OutputStream outputStream);
}

package parsers;

import model.parameters.OceanParameters;

import java.io.OutputStream;

public interface OceanParamsWriter {
    void write(OceanParameters oceanParameters, OutputStream outputStream);
}

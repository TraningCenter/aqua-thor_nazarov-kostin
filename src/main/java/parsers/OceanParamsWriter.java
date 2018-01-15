package parsers;

import model.parameters.OceanParameters;

import java.io.OutputStream;
/**
 * writes parameters in file
 */
public interface OceanParamsWriter {
    void write(OceanParameters oceanParameters, OutputStream outputStream);
}

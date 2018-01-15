package parsers;

import model.parameters.OceanParameters;

import java.io.InputStream;
/**
 * reads metrics from file
 */
public interface OceanParamsReader {
    OceanParameters read(InputStream inputStream);
}

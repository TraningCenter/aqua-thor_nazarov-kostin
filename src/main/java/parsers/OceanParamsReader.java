package parsers;

import model.parameters.OceanParameters;

import java.io.InputStream;

public interface OceanParamsReader {
    OceanParameters read(InputStream inputStream);
}

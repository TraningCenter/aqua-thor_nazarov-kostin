package parsers;

import model.OceanParameters;

import java.io.InputStream;

public interface OceanParamsReader {
    OceanParameters read(InputStream inputStream);
}

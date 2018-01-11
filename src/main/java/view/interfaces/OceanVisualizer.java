package view.interfaces;

import dto.OceanDto;

import java.io.IOException;

public interface OceanVisualizer {
    void visualize(OceanDto oceanDto) throws IOException;
}

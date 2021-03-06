package view.interfaces;

import dto.OceanDto;

import java.io.IOException;

/**
 * interface for Visualizing ocean dto
 */
public interface OceanVisualizer {
    boolean visualize(OceanDto oceanDto) throws IOException;
}

package controller.interfaces;

import dto.OceanDto;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Functional interface for functions, which have OceanDto argument and returns Boolean
 */
@FunctionalInterface
public interface AfterUpdateFunction extends Function<OceanDto,Boolean> {
}

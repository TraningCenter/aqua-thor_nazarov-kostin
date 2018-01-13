package controller.interfaces;

import dto.OceanDto;

import java.util.function.Consumer;
import java.util.function.Function;

@FunctionalInterface
public interface AfterUpdateFunction extends Function<OceanDto,Boolean> {
}

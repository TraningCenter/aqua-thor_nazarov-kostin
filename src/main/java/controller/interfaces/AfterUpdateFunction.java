package controller.interfaces;

import dto.OceanDto;

import java.util.function.Consumer;

@FunctionalInterface
public interface AfterUpdateFunction extends Consumer<OceanDto> {
}

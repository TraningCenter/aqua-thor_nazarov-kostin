package model.fish.interfaces;

import model.cell.interfaces.RelativeCell;
import model.fish.implementation.TargetPriority;

@FunctionalInterface
public interface TargetPriorityCalcFunction {
    TargetPriority calcTargetPriority(RelativeCell relativeCell);
}

package model.fish.interfaces.target;

import model.cell.interfaces.RelativeCell;
import model.fish.implementation.target.TargetPriority;

@FunctionalInterface
public interface TargetPriorityCalcFunction {
    TargetPriority calcTargetPriority(RelativeCell relativeCell);
}

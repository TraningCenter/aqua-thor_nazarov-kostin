package model.fish.implementation.target;

import model.cell.interfaces.RelativeCell;
import model.fish.interfaces.target.TargetPriorityCalcFunction;

public class PassiveTargetPriorityCalcFunction implements TargetPriorityCalcFunction {
    @Override
    public TargetPriority calcTargetPriority(RelativeCell relativeCell) {
        return TargetPriority.HIGH;
    }
}

package model.fish.implementation.target;

import model.cell.interfaces.RelativeCell;
import model.fish.implementation.FishType;
import model.fish.interfaces.target.TargetPriorityCalcFunction;

/**
 * Function for calculating target priority for passive fish
 * if there is aggressive fish in range then priority is high
 */
public class PassiveTargetPriorityCalcFunction implements TargetPriorityCalcFunction {
    @Override
    public TargetPriority calcTargetPriority(RelativeCell relativeCell) {
        if (relativeCell.getFishes().stream().anyMatch(fish -> fish.getType()== FishType.AGGRESSIVE))
            return TargetPriority.HIGH;
        return TargetPriority.LOW;
    }
}

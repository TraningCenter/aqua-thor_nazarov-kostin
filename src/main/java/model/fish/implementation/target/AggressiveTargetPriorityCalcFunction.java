package model.fish.implementation.target;

import model.cell.interfaces.RelativeCell;
import model.fish.implementation.FishType;
import model.fish.interfaces.target.TargetPriorityCalcFunction;

/***
 * Function for calculating target priority for aggressive fish
 * if there is passive fish in range then priority is high
 */
public class AggressiveTargetPriorityCalcFunction implements TargetPriorityCalcFunction {
    @Override
    public TargetPriority calcTargetPriority(RelativeCell relativeCell) {
        if (relativeCell.getFishes().stream().anyMatch(fish -> fish.getType()== FishType.PASSIVE))
            return TargetPriority.HIGH;
        return TargetPriority.LOW;
    }
}

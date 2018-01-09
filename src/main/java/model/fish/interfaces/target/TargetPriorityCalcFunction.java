package model.fish.interfaces.target;

import model.cell.interfaces.RelativeCell;
import model.fish.implementation.target.TargetPriority;

/***
 * Function for calculating target priority for cell
 */
@FunctionalInterface
public interface TargetPriorityCalcFunction {
    TargetPriority calcTargetPriority(RelativeCell relativeCell);
}

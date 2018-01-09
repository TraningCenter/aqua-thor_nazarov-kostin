package model.fish.interfaces.target;

import model.cell.interfaces.RelativeCell;
import model.fish.implementation.target.Target;
import model.ocean.interfaces.OceanSpace;
import model.parameters.Vector;

import java.util.function.Predicate;

/***
 * Strategy for calculation next target in range
 */
public interface TargetCalculationFishStrategy {
    Target calculateTarget(Vector currentPosition, Integer range, OceanSpace oceanSpace,
                           Predicate<RelativeCell> cellPredicate, TargetPriorityCalcFunction targetPriorityCalcFunction);
}

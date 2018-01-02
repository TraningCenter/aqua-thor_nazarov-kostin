package model.fish.interfaces;

import model.cell.interfaces.RelativeCell;
import model.fish.implementation.Target;
import model.ocean.interfaces.OceanSpace;
import model.parameters.Vector;

import java.util.function.Predicate;

public interface TargetCalculationFishStrategy {
    Target calculateTarget(Vector currentPosition, Integer range, OceanSpace oceanSpace,
                           Predicate<RelativeCell> cellPredicate, TargetPriorityCalcFunction targetPriorityCalcFunction);
}

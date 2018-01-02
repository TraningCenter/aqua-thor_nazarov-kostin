package model.fish.implementation;

import model.cell.interfaces.Cell;
import model.cell.interfaces.RelativeCell;
import model.fish.interfaces.TargetCalculationFishStrategy;
import model.fish.interfaces.TargetPriorityCalcFunction;
import model.ocean.interfaces.OceanSpace;
import model.parameters.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BorderedTargetCalculationFishStrategy implements TargetCalculationFishStrategy {
    @Override
    public Target calculateTarget(Vector currentPosition, Integer range, OceanSpace oceanSpace, Predicate<RelativeCell> cellPredicate, TargetPriorityCalcFunction targetPriorityCalcFunction) {

        Optional<RelativeCell> optionalRelativeCell = oceanSpace.getCellsInRange(currentPosition, range)
                .stream()
                .filter(cellPredicate)
                .findFirst();

        if (optionalRelativeCell.isPresent())
            return new Target(optionalRelativeCell.get().getPosition(),
                        targetPriorityCalcFunction.calcTargetPriority(optionalRelativeCell.get()));

        return null;
    }
}

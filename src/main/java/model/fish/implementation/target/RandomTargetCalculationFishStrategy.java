package model.fish.implementation.target;

import model.cell.interfaces.RelativeCell;
import model.fish.interfaces.target.TargetCalculationFishStrategy;
import model.fish.interfaces.target.TargetPriorityCalcFunction;
import model.ocean.interfaces.OceanSpace;
import model.parameters.Vector;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RandomTargetCalculationFishStrategy implements TargetCalculationFishStrategy {

    @Override
    public Target calculateTarget(Vector currentPosition, Integer range, OceanSpace oceanSpace, Predicate<RelativeCell> cellPredicate, TargetPriorityCalcFunction targetPriorityCalcFunction) {

        List<RelativeCell> listOfRelativeCells = oceanSpace.getCellsInRange(currentPosition, range)
                .stream()
                .filter(relativeCell -> cellPredicate.test(relativeCell) && !relativeCell.getRelativePosition().equals(Vector.Zero()))
                .collect(Collectors.toList());

        if (listOfRelativeCells.size()>0){
            RelativeCell cell = getRandomCellFromList(listOfRelativeCells);

            return new Target(cell.getPosition(),
                    targetPriorityCalcFunction.calcTargetPriority(cell));

        }

        return null;
    }

    private RelativeCell getRandomCellFromList(List<RelativeCell> list) {
        return list.get(new Random().nextInt(list.size()));
    }
}

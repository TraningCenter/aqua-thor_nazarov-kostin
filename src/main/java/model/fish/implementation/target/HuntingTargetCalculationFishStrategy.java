package model.fish.implementation.target;

import model.cell.interfaces.RelativeCell;
import model.fish.implementation.FishType;
import model.fish.interfaces.target.TargetCalculationFishStrategy;
import model.fish.interfaces.target.TargetPriorityCalcFunction;
import model.ocean.interfaces.OceanSpace;
import model.parameters.Vector;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/***
 * Strategy for calculation hunt target if passive fish in range or random target
 */
public class HuntingTargetCalculationFishStrategy implements TargetCalculationFishStrategy {

    @Override
    public Target calculateTarget(Vector currentPosition, Integer range, OceanSpace oceanSpace, Predicate<RelativeCell> cellPredicate, TargetPriorityCalcFunction targetPriorityCalcFunction) {

        List<RelativeCell> cellsInRange = oceanSpace.getCellsInRange(currentPosition, range);

        Optional<RelativeCell> nearestCellWithFish = cellsInRange.stream()
                .filter(cell -> cell.getFishes().stream()
                        .anyMatch(fish -> fish.getType() == FishType.PASSIVE))
                .min((cell1, cell2) -> compareDistance(cell1,cell2));

        if (nearestCellWithFish.isPresent())
            return new Target(nearestCellWithFish.get().getPosition(),
                targetPriorityCalcFunction.calcTargetPriority(nearestCellWithFish.get()));

        List<RelativeCell> listOfRelativeCells = cellsInRange.stream()
                .filter(relativeCell -> cellPredicate.test(relativeCell) && !relativeCell.getRelativePosition().equals(Vector.Zero()))
                .collect(Collectors.toList());

        if (listOfRelativeCells.size()>0){

            RelativeCell cell = getRandomCellFromList(listOfRelativeCells);

            return new Target(cell.getPosition(),
                    targetPriorityCalcFunction.calcTargetPriority(cell));

        }

        return null;
    }

    private int compareDistance(RelativeCell cell1, RelativeCell cell2) {

        double distance1 = Math.sqrt(Math.pow(cell1.getRelativePosition().getX(), 2) + Math.pow(cell1.getRelativePosition().getY(), 2));
        double distance2 = Math.sqrt(Math.pow(cell2.getRelativePosition().getX(), 2) + Math.pow(cell2.getRelativePosition().getY(), 2));

        if (distance1<distance2)
            return -1;
            else if (distance2>distance1)
                return 1;

        return 0;
    }

    private RelativeCell getRandomCellFromList(List<RelativeCell> list) {
        return list.get(new Random().nextInt(list.size()));
    }


}

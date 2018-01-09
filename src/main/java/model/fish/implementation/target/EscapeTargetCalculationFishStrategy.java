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
import java.util.stream.Stream;

/***
 * Strategy for calculation escape target if aggressive fish in range else random target
 */
public class EscapeTargetCalculationFishStrategy implements TargetCalculationFishStrategy {

    @Override
    public Target calculateTarget(Vector currentPosition, Integer range, OceanSpace oceanSpace, Predicate<RelativeCell> cellPredicate, TargetPriorityCalcFunction targetPriorityCalcFunction) {

        List<RelativeCell> cellsInRange = oceanSpace.getCellsInRange(currentPosition, range);

        Optional<RelativeCell> nearestCellWithFish = cellsInRange.stream()
                .filter(cell -> cell.getFishes().stream()
                        .anyMatch(fish -> fish.getType() == FishType.AGGRESSIVE))
                .min((cell1, cell2) -> (int) Math.signum(cell1.getPosition().distanceTo(currentPosition) - cell2.getPosition().distanceTo(currentPosition)));


        if (nearestCellWithFish.isPresent()){
            RelativeCell cell = nearestCellWithFish.get();

            Vector cellToRunRelativePosition = new Vector(-cell.getRelativePosition().getX(), -cell.getRelativePosition().getY());
            Optional<RelativeCell> cellToRunOptional = cellsInRange.stream().filter(cellTemp -> cellTemp.getRelativePosition().equals(cellToRunRelativePosition)).findFirst();

            if (cellToRunOptional.isPresent())
                return new Target(cellToRunOptional.get().getPosition(),
                        targetPriorityCalcFunction.calcTargetPriority(cellToRunOptional.get()));
        }

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


    private RelativeCell getRandomCellFromList(List<RelativeCell> list) {
        return list.get(new Random().nextInt(list.size()));
    }
}

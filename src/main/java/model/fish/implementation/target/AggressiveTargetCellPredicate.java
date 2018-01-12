package model.fish.implementation.target;

import model.cell.interfaces.RelativeCell;
import model.fish.interfaces.target.TargetCellPredicate;

/**
 * Predicate testing cell for accessibility, fish can access any cell
 */
public class AggressiveTargetCellPredicate implements TargetCellPredicate {
    @Override
    public boolean test(RelativeCell relativeCell) {
        return true;
    }
}

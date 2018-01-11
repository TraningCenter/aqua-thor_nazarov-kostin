package model.fish.implementation.target;

import model.cell.interfaces.RelativeCell;
import model.fish.interfaces.target.TargetCellPredicate;

public class PassiveTargetCellPredicate implements TargetCellPredicate {
    @Override
    public boolean test(RelativeCell relativeCell) {
        return true;
    }
}
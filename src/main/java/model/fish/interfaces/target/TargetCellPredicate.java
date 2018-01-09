package model.fish.interfaces.target;

import model.cell.interfaces.RelativeCell;

import java.util.function.Predicate;

/***
 * Predicate testing cell for accessibility
 */
public interface TargetCellPredicate extends Predicate<RelativeCell> {
}

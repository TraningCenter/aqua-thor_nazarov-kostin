package model.cell.interfaces;

import model.parameters.Vector;
/**
 * interface with data for one relative cell
 */
public interface RelativeCell extends Cell {
    Vector getRelativePosition();
}

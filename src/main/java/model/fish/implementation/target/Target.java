package model.fish.implementation.target;

import model.parameters.Vector;

import java.util.Objects;

/***
 * Class which implementing target for fish
 * has priority and position
 */
public class Target {
    private Vector targetPosition;
    private TargetPriority targetPriority;

    public Target(Vector targetPosition, TargetPriority targetPriority) {
        this.targetPosition = targetPosition;
        this.targetPriority = targetPriority;
    }

    public Vector getPosition() {
        return targetPosition;
    }

    public void setPosition(Vector targetPosition) {
        this.targetPosition = targetPosition;
    }

    public TargetPriority getPriority() {
        return targetPriority;
    }

    public void setPriority(TargetPriority targetPriority) {
        this.targetPriority = targetPriority;
    }

    @Override
    public String toString() {
        return "Target{" +
                "targetPosition=" + targetPosition +
                ", targetPriority=" + targetPriority +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Target target = (Target) o;
        return Objects.equals(targetPosition, target.targetPosition) &&
                targetPriority == target.targetPriority;
    }

    @Override
    public int hashCode() {

        return Objects.hash(targetPosition, targetPriority);
    }
}

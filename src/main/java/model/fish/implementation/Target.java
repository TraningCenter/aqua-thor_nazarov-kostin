package model.fish.implementation;

import model.parameters.Vector;

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
}

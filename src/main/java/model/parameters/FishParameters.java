package model.parameters;

public class FishParameters {
    private Integer reproductionPeriodTicks;
    private Integer lifeTimeTicks;
    private Integer starvationTimeTicks;
    private Integer smellSenseDistance;
    private Integer timeToMoveThroughOneCell;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FishParameters that = (FishParameters) o;

        if (!reproductionPeriodTicks.equals(that.reproductionPeriodTicks)) return false;
        if (!lifeTimeTicks.equals(that.lifeTimeTicks)) return false;
        if (!starvationTimeTicks.equals(that.starvationTimeTicks)) return false;
        return smellSenseDistance.equals(that.smellSenseDistance);
    }

    @Override
    public int hashCode() {
        int result = reproductionPeriodTicks.hashCode();
        result = 31 * result + lifeTimeTicks.hashCode();
        result = 31 * result + starvationTimeTicks.hashCode();
        result = 31 * result + smellSenseDistance.hashCode();
        return result;
    }

    public FishParameters(){}

    public FishParameters(Integer reproductionPeriodTicks, Integer lifeTimeTicks, Integer starvationTimeTicks, Integer smellSenseDistance, Integer timeToMoveThroughOneCell) {
        this.reproductionPeriodTicks = reproductionPeriodTicks;
        this.lifeTimeTicks = lifeTimeTicks;
        this.starvationTimeTicks = starvationTimeTicks;
        this.smellSenseDistance = smellSenseDistance;
        this.timeToMoveThroughOneCell = timeToMoveThroughOneCell;
    }

    public Integer getStarvationTimeTicks() {
        return starvationTimeTicks;
    }

    public void setStarvationTimeTicks(Integer starvationTimeTicks) {
        this.starvationTimeTicks = starvationTimeTicks;
    }

    public Integer getLifeTimeTicks() {
        return lifeTimeTicks;
    }

    public void setLifeTimeTicks(Integer lifeTimeTicks) {
        this.lifeTimeTicks = lifeTimeTicks;
    }

    public Integer getReproductionPeriodTicks() {
        return reproductionPeriodTicks;
    }

    public void setReproductionPeriodTicks(Integer reproductionPeriodTicks) {
        this.reproductionPeriodTicks = reproductionPeriodTicks;
    }

    public Integer getSmellSenseDistance() {
        return smellSenseDistance;
    }

    public void setSmellSenseDistance(Integer smellSenseDistance) {
        this.smellSenseDistance = smellSenseDistance;
    }

    public Integer getTimeToMoveThroughOneCell() {
        return timeToMoveThroughOneCell;
    }

    public void setTimeToMoveThroughOneCell(Integer timeToMoveThroughOneCell) {
        this.timeToMoveThroughOneCell = timeToMoveThroughOneCell;
    }
}

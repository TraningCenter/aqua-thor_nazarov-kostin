package model;

public class FishParameters {
    private Integer reproductionPeriodTicks;
    private Integer lifeTimeTicks;
    private Integer starvationTimeTicks;
    private Integer smellSenseDistance;

    public FishParameters(Integer reproductionPeriodTicks, Integer lifeTimeTicks, Integer starvationTimeTicks, Integer smellSenseDistance) {
        this.reproductionPeriodTicks = reproductionPeriodTicks;
        this.lifeTimeTicks = lifeTimeTicks;
        this.starvationTimeTicks = starvationTimeTicks;
        this.smellSenseDistance = smellSenseDistance;
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
}

package model;

public class OceanParameters {
    private Vector oceanSize;
    private Flow flow;
    private Integer passiveFishCount;
    private Integer aggressiveFishCount;
    private OceanType oceanType;

    public OceanParameters(Vector oceanSize, Flow flow, Integer passiveFishCount, Integer aggressiveFishCount, OceanType oceanType) {
        this.oceanSize = oceanSize;
        this.flow = flow;
        this.passiveFishCount = passiveFishCount;
        this.aggressiveFishCount = aggressiveFishCount;
        this.oceanType = oceanType;
    }

    public Vector getOceanSize() {
        return oceanSize;
    }

    public void setOceanSize(Vector oceanSize) {
        this.oceanSize = oceanSize;
    }

    public Flow getFlow() {
        return flow;
    }

    public void setFlow(Flow flow) {
        this.flow = flow;
    }

    public Integer getPassiveFishCount() {
        return passiveFishCount;
    }

    public void setPassiveFishCount(Integer passiveFishCount) {
        this.passiveFishCount = passiveFishCount;
    }

    public Integer getAggressiveFishCount() {
        return aggressiveFishCount;
    }

    public void setAggressiveFishCount(Integer aggressiveFishCount) {
        this.aggressiveFishCount = aggressiveFishCount;
    }

    public OceanType getOceanType() {
        return oceanType;
    }

    public void setOceanType(OceanType oceanType) {
        this.oceanType = oceanType;
    }
}

package model;

public class OceanParameters {
    private Vector oceanSize;
    private Flow flow;
    private Integer passiveFishCount;
    private Integer aggressiveFishCount;
    private FishParameters passiveFishParameters;
    private FishParameters aggressiveFishParameters;
    private OceanType oceanType;

    //тупо чтобы тестить
    public static OceanParameters getTestParams(){
        OceanParameters oceanParameters = new OceanParameters();
        oceanParameters.setOceanSize(new Vector(20,10));
        oceanParameters.setFlow(new Flow(new Vector(1,0), 1));
        oceanParameters.setPassiveFishCount(15);
        oceanParameters.setAggressiveFishCount(5);
        oceanParameters.setPassiveFishParameters(new FishParameters(10,100,10,3));
        oceanParameters.setAggressiveFishParameters(new FishParameters(140,150,5,3));
        oceanParameters.setOceanType(OceanType.BORDERLESS);
        return oceanParameters;
    }

    public OceanParameters(){

    }

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

    public FishParameters getAggressiveFishParameters() {
        return aggressiveFishParameters;
    }

    public void setAggressiveFishParameters(FishParameters aggressiveFishParameters) {
        this.aggressiveFishParameters = aggressiveFishParameters;
    }

    public FishParameters getPassiveFishParameters() {
        return passiveFishParameters;
    }

    public void setPassiveFishParameters(FishParameters passiveFishParameters) {
        this.passiveFishParameters = passiveFishParameters;
    }
}

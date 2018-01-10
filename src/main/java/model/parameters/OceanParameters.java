package model.parameters;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;


@XmlRootElement
@XmlType(propOrder = {"oceanType", "oceanSize","flows", "passiveFishCount", "aggressiveFishCount", "passiveFishParameters", "aggressiveFishParameters"})
public class OceanParameters {

    private Vector oceanSize;
    private List<Flow> flows;
    private OceanType oceanType;
    private Integer passiveFishCount;
    private Integer aggressiveFishCount;
    private FishParameters passiveFishParameters;
    private FishParameters aggressiveFishParameters;

    public OceanParameters(){}

    public OceanParameters(Vector oceanSize, List<Flow> flows, Integer passiveFishCount, Integer aggressiveFishCount, OceanType oceanType) {
        this.oceanSize = oceanSize;
        this.flows = flows;
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

    @XmlElementWrapper(name = "flows")
    @XmlElement(name = "flow")
    public List<Flow> getFlows() {
        return flows;
    }

    public void setFlows(List<Flow> flows) {
        this.flows = flows;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OceanParameters that = (OceanParameters) o;

        if (!oceanSize.equals(that.oceanSize)) return false;
        if (!flows.equals(that.flows)) return false;
        if (oceanType != that.oceanType) return false;
        if (!passiveFishCount.equals(that.passiveFishCount)) return false;
        if (!aggressiveFishCount.equals(that.aggressiveFishCount)) return false;
        if (!passiveFishParameters.equals(that.passiveFishParameters)) return false;
        return aggressiveFishParameters.equals(that.aggressiveFishParameters);
    }

    @Override
    public int hashCode() {
        int result = oceanSize.hashCode();
        result = 31 * result + oceanType.hashCode();
       for (Flow flow: flows) {
           result = 31 * result + flow.hashCode();
       }
        result = 31 * result + passiveFishCount.hashCode();
        result = 31 * result + aggressiveFishCount.hashCode();
        result = 31 * result + passiveFishParameters.hashCode();
        result = 31 * result + aggressiveFishParameters.hashCode();
        return result;
    }
}

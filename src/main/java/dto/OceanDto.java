package dto;


import model.parameters.OceanType;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "oceanMetrics")
@XmlType(propOrder = {"oceanType", "stepCount", "fishCount","sharkCount"})
public class OceanDto {

    private int fishCount;
    private int sharkCount;
    private int stepCount;
    private OceanType oceanType;

    public int getFishCount() {
        return fishCount;
    }

    public void setFishCount(int fishCount) {
        this.fishCount = fishCount;
    }

    public int getSharkCount() {
        return sharkCount;
    }

    public void setSharkCount(int sharkCount) {
        this.sharkCount = sharkCount;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public OceanType getOceanType() {
        return oceanType;
    }

    public void setOceanType(OceanType oceanType) {
        this.oceanType = oceanType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OceanDto that = (OceanDto) o;


        if (oceanType != that.oceanType) return false;
        if (fishCount != that.fishCount) return false;
        if (sharkCount != that.sharkCount) return false;
        return stepCount==that.stepCount;
    }

    @Override
    public int hashCode() {
        int result = oceanType.hashCode();
        result = 31 * result + stepCount;
        result = 31 * result + fishCount;
        result = 31 * result + sharkCount;
        return result;
    }
}

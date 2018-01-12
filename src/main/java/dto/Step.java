package dto;

import model.fish.implementation.FishType;
import model.parameters.OceanType;

import javax.xml.bind.annotation.XmlType;
import java.util.List;


@XmlType(propOrder = {"stepCount","fishCount","sharkCount"})
public class Step {



    private int stepCount;
    private int fishCount=0;
    private int sharkCount=0;


    public Step() {
    }

    public Step(int stepCount, List<FishDto> fishes) {
        this.stepCount = stepCount;
        for(FishDto fish:fishes){
            if (fish.getFishType()== FishType.PASSIVE){
                fishCount++;
            }else {
                sharkCount++;
            }
        }
    }

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




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Step that = (Step) o;

        if (fishCount != that.fishCount) return false;
        if (sharkCount != that.sharkCount) return false;
        return stepCount==that.stepCount;
    }

    @Override
    public int hashCode() {
        int result = 31 * stepCount;
        result = 31 * result + fishCount;
        result = 31 * result + sharkCount;
        return result;
    }

}

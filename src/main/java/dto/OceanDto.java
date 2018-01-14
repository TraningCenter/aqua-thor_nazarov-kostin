package dto;


import model.fish.implementation.FishType;
import model.fish.interfaces.Fish;
import model.parameters.Flow;
import model.parameters.OceanType;
import model.parameters.Vector;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * class for store special ocean's parameters for rendering and saving
 */
@XmlRootElement(name = "oceanMetrics")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {"oceanType", "steps"})
public class OceanDto {

    public OceanDto() {
        steps= new ArrayList<>();
    }

    private OceanType oceanType;

    private Vector oceanSize;

    private List<Flow> flows;

    private List<FishDto> fishes;

    private List<Step> steps;

    private int stepsCount = 0;

    public void addStep(){

        steps.add(new Step(stepsCount,fishes));
    }

    public void incrementStepCount(){
        stepsCount++;
    }

    public OceanType getOceanType() {
        return oceanType;
    }

    @XmlElement
    public void setOceanType(OceanType oceanType) {
        this.oceanType = oceanType;
    }

    public Vector getOceanSize() {
        return oceanSize;
    }

    public void setOceanSize(Vector oceanSize) {
        this.oceanSize = oceanSize;
    }

    public List<Flow> getFlows() {
        return flows;
    }

    public void setFlows(List<Flow> flows) {
        this.flows = flows;
    }

    public List<FishDto> getFishes() {
        return fishes;
    }

    public void setFishes(List<FishDto> fishes) {
        this.fishes = fishes;
    }


    @XmlElementWrapper(name = "steps")
    @XmlElement(name = "step")
    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Integer getSharkCount() {
        return Math.toIntExact(fishes.stream().filter(fish -> fish.getFishType() == FishType.AGGRESSIVE).count());
    }

    public Integer getStepCount() {
        return steps.size();
    }

    public Integer getFishCount() {
        return Math.toIntExact(fishes.stream().filter(fish -> fish.getFishType() == FishType.PASSIVE).count());
    }


    public int getStepsCount() {
        return stepsCount;
    }
}

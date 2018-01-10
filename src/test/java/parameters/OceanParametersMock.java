package parameters;

import model.parameters.*;
import writers.implementation.XmlParamsPrinter;

import java.util.ArrayList;
import java.util.List;

public class OceanParametersMock {

    public OceanParameters getParameters() {

        OceanParameters oceanParameters = new OceanParameters();

        oceanParameters.setOceanType(OceanType.BORDERED);
        oceanParameters.setAggressiveFishCount(10);
        oceanParameters.setPassiveFishCount(15);
        oceanParameters.setOceanSize(new Vector(100, 100));
        Flow flow1 = new Flow();
        flow1.setRectangle(new Rectangle(10, 30, 50, 20));
        flow1.setDirection(new Vector(4, 5));
        flow1.setStrength(3);
        Flow flow2 = new Flow();
        flow2.setRectangle(new Rectangle(20, 5, 30, 40));
        flow2.setDirection(new Vector(5, 9));
        flow2.setStrength(1);
        List<Flow> flows = new ArrayList<>();
        flows.add(flow1);
        flows.add(flow2);
        oceanParameters.setFlows(flows);
        FishParameters passiveParam = new FishParameters();
        passiveParam.setLifeTimeTicks(10);
        passiveParam.setReproductionPeriodTicks(3);
        passiveParam.setSmellSenseDistance(10);
        passiveParam.setStarvationTimeTicks(4);
        passiveParam.setTimeToMoveThroughOneCell(5);
        FishParameters agressiveParam = new FishParameters();
        agressiveParam.setLifeTimeTicks(9);
        agressiveParam.setReproductionPeriodTicks(2);
        agressiveParam.setSmellSenseDistance(7);
        agressiveParam.setStarvationTimeTicks(1);
        agressiveParam.setTimeToMoveThroughOneCell(7);
        oceanParameters.setPassiveFishParameters(passiveParam);
        oceanParameters.setAggressiveFishParameters(agressiveParam);


        return oceanParameters;
    }
}
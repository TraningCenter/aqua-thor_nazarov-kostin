import model.*;

public class TestUtil {

    static private String xmlBody = "<oceanType>BORDERLESS</oceanType><oceanSize><x>20</x><y>10</y></oceanSize><flow><direction><x>1</x><y>0</y></direction><strength>1</strength></flow><passiveFishCount>15</passiveFishCount><aggressiveFishCount>5</aggressiveFishCount><passiveFishParameters><lifeTimeTicks>100</lifeTimeTicks><reproductionPeriodTicks>10</reproductionPeriodTicks><smellSenseDistance>3</smellSenseDistance><starvationTimeTicks>10</starvationTimeTicks></passiveFishParameters><aggressiveFishParameters><lifeTimeTicks>150</lifeTimeTicks><reproductionPeriodTicks>140</reproductionPeriodTicks><smellSenseDistance>3</smellSenseDistance><starvationTimeTicks>5</starvationTimeTicks></aggressiveFishParameters>";

    public static String getTestXmlString() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><oceanParameters><oceanType>BORDERLESS</oceanType><oceanSize><x>20</x><y>10</y></oceanSize><flow><direction><x>1</x><y>0</y></direction><strength>1</strength></flow><passiveFishCount>15</passiveFishCount><aggressiveFishCount>5</aggressiveFishCount><passiveFishParameters><lifeTimeTicks>100</lifeTimeTicks><reproductionPeriodTicks>10</reproductionPeriodTicks><smellSenseDistance>3</smellSenseDistance><starvationTimeTicks>10</starvationTimeTicks></passiveFishParameters><aggressiveFishParameters><lifeTimeTicks>150</lifeTimeTicks><reproductionPeriodTicks>140</reproductionPeriodTicks><smellSenseDistance>3</smellSenseDistance><starvationTimeTicks>5</starvationTimeTicks></aggressiveFishParameters></oceanParameters>";
    }


    public static String getTestXmlWithStandaloneNoString() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><oceanParameters><oceanType>BORDERLESS</oceanType><oceanSize><x>20</x><y>10</y></oceanSize><flow><direction><x>1</x><y>0</y></direction><strength>1</strength></flow><passiveFishCount>15</passiveFishCount><aggressiveFishCount>5</aggressiveFishCount><passiveFishParameters><lifeTimeTicks>100</lifeTimeTicks><reproductionPeriodTicks>10</reproductionPeriodTicks><smellSenseDistance>3</smellSenseDistance><starvationTimeTicks>10</starvationTimeTicks></passiveFishParameters><aggressiveFishParameters><lifeTimeTicks>150</lifeTimeTicks><reproductionPeriodTicks>140</reproductionPeriodTicks><smellSenseDistance>3</smellSenseDistance><starvationTimeTicks>5</starvationTimeTicks></aggressiveFishParameters></oceanParameters>";
    }

    public static String getTestXmlWithoutStandaloneString() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><oceanParameters><oceanType>BORDERLESS</oceanType><oceanSize><x>20</x><y>10</y></oceanSize><flow><direction><x>1</x><y>0</y></direction><strength>1</strength></flow><passiveFishCount>15</passiveFishCount><aggressiveFishCount>5</aggressiveFishCount><passiveFishParameters><lifeTimeTicks>100</lifeTimeTicks><reproductionPeriodTicks>10</reproductionPeriodTicks><smellSenseDistance>3</smellSenseDistance><starvationTimeTicks>10</starvationTimeTicks></passiveFishParameters><aggressiveFishParameters><lifeTimeTicks>150</lifeTimeTicks><reproductionPeriodTicks>140</reproductionPeriodTicks><smellSenseDistance>3</smellSenseDistance><starvationTimeTicks>5</starvationTimeTicks></aggressiveFishParameters></oceanParameters>";
    }

    public static String getBigTestXmlString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><oceanParameters>");
        for (int i = 0; i < 1500; i++) {
            sb.append(xmlBody);
        }
        sb.append("</oceanParameters>");
        return sb.toString();
    }

    public static OceanParameters getTestOceanParameters() {
        OceanParameters initOceanParameters = new OceanParameters();
        initOceanParameters.setOceanSize(new Vector(20, 10));
        initOceanParameters.setFlow(new Flow(new Vector(1, 0), 1));
        initOceanParameters.setPassiveFishCount(15);
        initOceanParameters.setAggressiveFishCount(5);
        initOceanParameters.setPassiveFishParameters(new FishParameters(10, 100, 10, 3));
        initOceanParameters.setAggressiveFishParameters(new FishParameters(140, 150, 5, 3));
        initOceanParameters.setOceanType(OceanType.BORDERLESS);
        return initOceanParameters;
    }
}

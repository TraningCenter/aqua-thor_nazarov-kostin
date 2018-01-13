package controller.implementation;

import controller.interfaces.AfterUpdateFunction;
import controller.interfaces.OceanRunner;
import dto.OceanDto;
import dto.translators.OceanTranslator;
import model.ocean.interfaces.Ocean;
import model.parameters.OceanParameters;
import view.implementation.DefaultMenuVisualizer;
import writers.interfaces.MetricsPrinter;

import java.util.concurrent.TimeUnit;

/**
 * class for run ocean life, updates ocean state
 */
public class DefaultOceanRunner implements OceanRunner {

    private boolean isEnded = false;

    private Ocean ocean;
    private OceanDto oceanDto;
    private OceanTranslator translator;
    private MetricsPrinter metricsPrinter;

    private AfterUpdateFunction afterUpdateFunction;

    public DefaultOceanRunner(Ocean ocean, OceanParameters oceanParameters,
                              OceanTranslator translator,MetricsPrinter metricsPrinter, AfterUpdateFunction afterUpdateFunction) {
        oceanDto = translator.createOceanDto(oceanParameters);
        this.ocean = ocean;
        this.translator = translator;
        this.afterUpdateFunction = afterUpdateFunction;
        this.metricsPrinter = metricsPrinter;
    }

    @Override
    public void start() {
        try {
            while (!isEnded) {
                update();
                TimeUnit.MILLISECONDS.sleep(30);
            }
            DefaultMenuVisualizer menuVisualizer = new DefaultMenuVisualizer();
            menuVisualizer.visualizeStartMenu();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        isEnded = true;
    }

    private void update(){
        ocean.update();

        translator.translateToDto(oceanDto,ocean);
       oceanDto.incrementStepCount();
        if (oceanDto.getStepsCount()%100==1) {
            oceanDto.addStep();
            metricsPrinter.writeMetrics(oceanDto);
        }
       if ( afterUpdateFunction.apply(oceanDto)){
            stop();
       }

    }
}

package controller.implementation;

import configurator.implementation.DefaultParserChanger;
import configurator.implementation.XmlOceanCreator;
import configurator.interfaces.ParserChanger;
import controller.interfaces.AfterUpdateFunction;
import controller.interfaces.OceanRunner;
import dto.OceanDto;
import dto.translators.OceanTranslator;
import model.ocean.interfaces.Ocean;
import model.parameters.OceanParameters;
import readers.implementation.XmlParametersReader;
import readers.interfaces.ParametersReader;

import java.util.concurrent.TimeUnit;

public class DefaultOceanRunner implements OceanRunner {

    private boolean isEnded = false;

    private Ocean ocean;
    private OceanDto oceanDto;
    private OceanTranslator translator;

    private AfterUpdateFunction afterUpdateFunction;

    public DefaultOceanRunner(Ocean ocean, OceanParameters oceanParameters,
                              OceanTranslator translator, AfterUpdateFunction afterUpdateFunction) {
        oceanDto = translator.createOceanDto(oceanParameters);
        this.ocean = ocean;
        this.translator = translator;
        this.afterUpdateFunction = afterUpdateFunction;
    }

    @Override
    public void start() {
        try {
            while (!isEnded) {
                update();
                TimeUnit.MILLISECONDS.sleep(30);
            }
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
        oceanDto.addStep();

        afterUpdateFunction.accept(oceanDto);

    }
}

package controller.implementation;

import configurator.implementation.DefaultParserChanger;
import configurator.implementation.XmlOceanCreator;
import configurator.interfaces.ParserChanger;
import controller.interfaces.OceanRunner;
import dto.OceanDto;
import dto.translators.OceanTranslator;
import model.ocean.interfaces.Ocean;
import model.parameters.OceanParameters;
import readers.implementation.XmlParametersReader;
import readers.interfaces.ParametersReader;

public class DefaultOceanRunner implements OceanRunner {



    private Ocean ocean;
    private OceanDto oceanDto;
    private OceanTranslator translator;

    @Override
    public void start(){

        translator = new OceanTranslator();
        ParserChanger parserChanger = new DefaultParserChanger();
        XmlParametersReader reader = new XmlParametersReader();
        OceanParameters parameters = reader.readParams(new DefaultParserChanger());
        ocean = new XmlOceanCreator().createOcean(parserChanger,reader);
        oceanDto = translator.createOceanDto(parameters);
        while(true){
            update();
        }

    }

    private void update(){

        ocean.update();
        translator.translateToDto(oceanDto,ocean);
        oceanDto.addStep();

    }
}

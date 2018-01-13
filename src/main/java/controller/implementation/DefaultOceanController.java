package controller.implementation;

import configurator.implementation.DefaultParserChanger;
import configurator.implementation.XmlOceanCreator;
import configurator.interfaces.ParserChanger;
import controller.interfaces.OceanController;
import controller.interfaces.OceanRunner;
import dto.OceanDto;
import dto.translators.OceanTranslator;
import model.ocean.interfaces.Ocean;
import model.parameters.OceanParameters;
import readers.implementation.XmlParametersReader;
import view.implementation.DefaultOceanVisualizer;
import view.interfaces.OceanVisualizer;
import writers.implementation.XmlMetricsPrinter;
import writers.interfaces.MetricsPrinter;

import java.io.IOException;

/**
 * class for controlling parts of ocean simulation, like parameter reader, runner, visualizer
 */
public class DefaultOceanController implements OceanController {

    private final OceanRunner oceanRunner;
    private final OceanVisualizer oceanVisualizer;

    public DefaultOceanController() throws IOException {


        OceanTranslator translator = new OceanTranslator();
        ParserChanger parserChanger = new DefaultParserChanger();
        XmlParametersReader reader = new XmlParametersReader();
        OceanParameters parameters = reader.readParams(parserChanger);
        Ocean ocean = new XmlOceanCreator().createOcean(parserChanger, reader);
        MetricsPrinter metricsPrinter = new XmlMetricsPrinter(parserChanger);

        oceanRunner = new DefaultOceanRunner(ocean, parameters, translator,metricsPrinter, this::afterUpdate);

        oceanVisualizer = new DefaultOceanVisualizer(parameters.getOceanSize());
    }

    @Override
    public void run() {
        oceanRunner.start();
    }

    private boolean afterUpdate(OceanDto oceanDto){
        try {
           return oceanVisualizer.visualize(oceanDto);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

package writers.implementation;

import configurator.implementation.DefaultParserChanger;
import configurator.interfaces.ParserChanger;
import dto.OceanDto;
import parsers.MetricsWriter;
import writers.interfaces.MetricsPrinter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XmlMetricsPrinter implements MetricsPrinter {


    private String path = "OceanMetrics.xml";

    @Override
    public void writeMetrics(OceanDto oceanDto,ParserChanger parserChanger) {

        MetricsWriter metricsWriter;

        try (FileOutputStream fos = new FileOutputStream(path)) {
            metricsWriter = parserChanger.changeMetricsWriter();
            metricsWriter.write(oceanDto, fos);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setPath(String path) {
        this.path = path;
    }
}

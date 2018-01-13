package writers.implementation;

import configurator.interfaces.ParserChanger;
import dto.OceanDto;
import parsers.MetricsWriter;
import writers.interfaces.MetricsPrinter;

import java.io.FileOutputStream;
import java.io.IOException;

public class XmlMetricsPrinter implements MetricsPrinter {

    private ParserChanger parserChanger;
    private String path = "OceanMetrics.xml";

    public  XmlMetricsPrinter(ParserChanger  parserChanger){
        this.parserChanger =parserChanger;
    }

    @Override
    public void writeMetrics(OceanDto oceanDto) {

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

package configurator.implementation;

import configurator.interfaces.ParserChanger;
import parsers.MetricsWriter;
import parsers.OceanParamsReader;
import parsers.OceanParamsWriter;
import parsers.dom.DomMetricsWriter;
import parsers.dom.DomOceanParamsReader;
import parsers.dom.DomOceanParamsWriter;
import parsers.jaxb.JaxbMetricsWriter;
import parsers.jaxb.JaxbOceanParamsReader;
import parsers.jaxb.JaxbOceanParamsWriter;
import parsers.sax.SaxMetricsWriter;
import parsers.sax.SaxOceanParamsReader;
import parsers.sax.SaxOceanParamsWriter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultParserChanger implements ParserChanger{

   private Map<String,String> properties = new HashMap<>();

   private String path ="parserType.properties";

    protected Map<String,String> readPropertiesFile() throws IOException{

        StringBuilder nameBuffer = new StringBuilder();
        StringBuilder valueBuffer = new StringBuilder();
        int separatorIndex=0;
        List<String> lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        for(String line: lines){
            for(int i=0;i<line.length();i++){
                if(line.charAt(i)=='='){
                    separatorIndex=i;
                }
            }
            nameBuffer.append(line.substring(0,separatorIndex));
            valueBuffer.append(line.substring(separatorIndex+1,line.length()));
            properties.put(nameBuffer.toString(),valueBuffer.toString());
            nameBuffer.delete(0,nameBuffer.length());
            valueBuffer.delete(0,valueBuffer.length());
        }

        return properties;
    }

    public OceanParamsReader changeReader() throws IOException{
        OceanParamsReader oceanParamsReader;
      properties = readPropertiesFile();
      if (properties.get("OceanParamsReader")!=null) {
          switch (properties.get("OceanParamsReader")) {
              case "SAX":
                  oceanParamsReader = new SaxOceanParamsReader();
                  break;
              case "DOM":
                  oceanParamsReader = new DomOceanParamsReader();
                  break;
              case "JAXB":
                  oceanParamsReader = new JaxbOceanParamsReader();
                  break;
              default:
                  oceanParamsReader = new DomOceanParamsReader();
                  break;
          }
      }else
      {
          oceanParamsReader = new SaxOceanParamsReader();
      }
      return oceanParamsReader;
    }

    public OceanParamsWriter changeWriter() throws IOException{
        OceanParamsWriter oceanParamsWriter;
        properties = readPropertiesFile();
        if (properties.get("OceanParamsWriter")!=null) {
            switch (properties.get("OceanParamsWriter")) {
                case "SAX":
                    oceanParamsWriter = new SaxOceanParamsWriter();
                    break;
                case "DOM":
                    oceanParamsWriter = new DomOceanParamsWriter();
                    break;
                case "JAXB":
                    oceanParamsWriter = new JaxbOceanParamsWriter();
                    break;
                default:
                    oceanParamsWriter = new DomOceanParamsWriter();
                    break;
            }
        }else{
            oceanParamsWriter= new SaxOceanParamsWriter();
        }
        return oceanParamsWriter;
    }


    public MetricsWriter changeMetricsWriter() throws IOException{
        MetricsWriter metricsWriter;
        properties = readPropertiesFile();
        if (properties.get("MetricsWriter")!=null) {
            switch (properties.get("MetricsWriter")) {
                case "SAX":
                    metricsWriter = new SaxMetricsWriter();
                    break;
                case "DOM":
                    metricsWriter = new DomMetricsWriter();
                    break;
                case "JAXB":
                    metricsWriter = new JaxbMetricsWriter();
                    break;
                default:
                    metricsWriter = new DomMetricsWriter();
                    break;
            }
        }else{
            metricsWriter=new SaxMetricsWriter();
        }
        return metricsWriter;
    }

    public void setPath(String path) {
        this.path = path;
    }
}


package dto.translators;

import dto.OceanDto;
import model.ocean.interfaces.Ocean;
import model.parameters.OceanParameters;

public class OceanTranslator {



    private FishTranslator fishTranslator;

    public OceanDto translateToDto(OceanDto oceanDto,Ocean ocean){

        fishTranslator = new FishTranslator();

         oceanDto.setFishes(fishTranslator.translateToFishDto( ocean.getFishes()));

         return oceanDto;

    }

    public OceanDto createOceanDto(OceanParameters parameters){

        OceanDto oceanDto = new OceanDto();
        oceanDto.setOceanType(parameters.getOceanType());
        oceanDto.setFlows(parameters.getFlows());
        oceanDto.setOceanSize(parameters.getOceanSize());
        return oceanDto;
    }

}

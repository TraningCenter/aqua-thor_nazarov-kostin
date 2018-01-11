package dto.translators;

import dto.FishDto;
import model.fish.interfaces.Fish;
import model.parameters.FishState;

import java.util.ArrayList;
import java.util.List;

public class FishTranslator {

    private List<FishDto> fishDtos;

    public List<FishDto> translateToFishDto(List<Fish> fishes) {

        fishDtos = new ArrayList<>();
        for (Fish fish : fishes) {
            FishDto dto = new FishDto();
            dto.setFishType(fish.getType());
            dto.setPosition(fish.getCurrentPosition());
            dto.setFishState(fish.getState());
            fishDtos.add(dto);
        }
        return fishDtos;
    }

}
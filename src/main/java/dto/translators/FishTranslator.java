package dto.translators;

import dto.FishDto;
import model.fish.implementation.FishType;
import model.fish.implementation.OceanFish;
import model.fish.interfaces.Fish;

import java.util.ArrayList;
import java.util.List;

public class FishTranslator {

    private List<FishDto> fishDtos;

    public List<FishDto> translateToFishDto(List<Fish> fishes) {

        fishDtos = new ArrayList<>();
        for (Fish fish : fishes) {
            FishDto dto = new FishDto();
            dto.setFishType(fish.getType());
            dto.setCoord(fish.getCurrentPosition());
            fishDtos.add(dto);
        }
        return fishDtos;
    }
}
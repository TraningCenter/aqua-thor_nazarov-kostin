package visualizer;

import dto.FishDto;
import dto.OceanDto;
import model.fish.factory.BorderedFishFactory;
import model.fish.implementation.FishType;
import model.fish.interfaces.Fish;
import model.ocean.interfaces.OceanSpace;
import model.parameters.*;
import org.junit.Test;
import view.implementation.DefaultOceanVisualizer;
import view.interfaces.OceanVisualizer;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class VisualizerTests {

    public void canVisualizeOceanDto() throws IOException {
        //Array
        Vector oceanSize = new Vector(20,20);
        List<Flow> flows = new LinkedList<>();
        flows.add(new Flow(new Vector(1,0), 8, new Rectangle(1,1,8,8)));
        flows.add(new Flow(new Vector(-1,0), 8, new Rectangle(11,11,8,8)));
        List<FishDto> fishes = new LinkedList<>();
        fishes.add(new FishDto(FishType.PASSIVE, new Vector(7,6), FishState.BIRTH));
        fishes.add(new FishDto(FishType.PASSIVE, new Vector(8,5), FishState.EATING));
        fishes.add(new FishDto(FishType.PASSIVE, new Vector(9,4), FishState.MOVING));
        fishes.add(new FishDto(FishType.AGGRESSIVE, new Vector(11,13), FishState.BIRTH));
        fishes.add(new FishDto(FishType.AGGRESSIVE, new Vector(12,14), FishState.EATING));
        fishes.add(new FishDto(FishType.AGGRESSIVE, new Vector(13,15), FishState.MOVING));

        OceanDto oceanDto = new OceanDto();
        oceanDto.setOceanSize(oceanSize);
        oceanDto.setFlows(flows);
        oceanDto.setFishes(fishes);

        OceanVisualizer visualizer = new DefaultOceanVisualizer(oceanSize);

        //Act
        visualizer.visualize(oceanDto);



        //Assert
    }
}

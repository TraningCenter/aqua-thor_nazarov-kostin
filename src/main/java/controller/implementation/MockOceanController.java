package controller.implementation;

import configurator.implementation.DefaultFishesCreator;
import configurator.implementation.DefaultParserChanger;
import configurator.implementation.XmlOceanCreator;
import configurator.interfaces.FishesCreator;
import configurator.interfaces.OceanCreator;
import configurator.interfaces.ParserChanger;
import controller.interfaces.OceanController;
import controller.interfaces.OceanRunner;
import dto.OceanDto;
import dto.translators.OceanTranslator;
import model.cell.implementation.DefaultCell;
import model.cell.interfaces.Cell;
import model.fish.interfaces.Fish;
import model.grid.implementation.DefaultCellGrid;
import model.grid.interfaces.CellGrid;
import model.ocean.implementaion.BorderlessCellBehavior;
import model.ocean.implementaion.DefaultOcean;
import model.ocean.interfaces.CellsBehavior;
import model.ocean.interfaces.Ocean;
import model.ocean.interfaces.OceanSpace;
import model.parameters.*;
import readers.implementation.XmlParametersReader;
import view.implementation.DefaultOceanVisualizer;
import view.interfaces.OceanVisualizer;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class MockOceanController implements OceanController {

    private final OceanRunner oceanRunner;
    private final OceanVisualizer oceanVisualizer;

    public MockOceanController() throws IOException {
        Vector oceanSize = new Vector(50,20);

        List<Flow> flows = new LinkedList<>();
        flows.add(new Flow(new Vector(1,0), 2, new Rectangle(2,2,46,2)));
        flows.add(new Flow(new Vector(0,1), 2, new Rectangle(48,2,2,6)));
        flows.add(new Flow(new Vector(1,0), 2, new Rectangle(48,8,2,2)));
        flows.add(new Flow(new Vector(1,0), 2, new Rectangle(0,8,2,2)));
        flows.add(new Flow(new Vector(0,-1), 2, new Rectangle(2,4,2,6)));
        flows.add(new Flow(new Vector(0,-1), 2, new Rectangle(24,4,2,6)));


        OceanParameters parameters = new OceanParameters(oceanSize,flows,1,1, OceanType.BORDERLESS);
        parameters.setAggressiveFishParameters(new FishParameters(1200,2000, 1000, 2, 5));
        parameters.setPassiveFishParameters(new FishParameters(720,1500, 1000, 2, 5));

        CellsBehavior cellsBehavior = new BorderlessCellBehavior();
        CellGrid cellGrid = buildCellGrid(parameters.getOceanSize());
        DefaultOcean ocean = new DefaultOcean(cellsBehavior,parameters.getFlows(),cellGrid);

        FishesCreator fishesCreator = new DefaultFishesCreator();
        ocean.addAllFishes(fishesCreator.createFishes(parameters,ocean));

        oceanVisualizer = new DefaultOceanVisualizer(parameters.getOceanSize());

        oceanRunner = new DefaultOceanRunner(ocean, parameters, new OceanTranslator(),null, this::afterUpdate);
    }

    private CellGrid buildCellGrid(Vector oceanSize){
        Cell[][] cells= new Cell[oceanSize.getX()][oceanSize.getY()];
        for (int i=0;i<oceanSize.getX();i++){
            for (int j=0;j<oceanSize.getY();j++){
                cells[i][j] = new DefaultCell(new Vector(i,j));
            }
        }
        return new DefaultCellGrid(cells);
    }

    @Override
    public void run() {
        oceanRunner.start();
    }

    private Boolean afterUpdate(OceanDto oceanDto) {
        try {
           return oceanVisualizer.visualize(oceanDto);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

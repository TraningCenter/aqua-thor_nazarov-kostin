package configurator.implementation;

import configurator.interfaces.FishesCreator;
import configurator.interfaces.OceanCreator;
import configurator.interfaces.ParserChanger;
import model.cell.implementation.DefaultCell;
import model.cell.interfaces.Cell;
import model.fish.interfaces.Fish;
import model.grid.implementation.DefaultCellGrid;
import model.grid.interfaces.CellGrid;
import model.ocean.implementaion.BorderedCellBehavior;
import model.ocean.implementaion.BorderlessCellBehavior;
import model.ocean.implementaion.DefaultOcean;
import model.ocean.interfaces.CellsBehavior;
import model.ocean.interfaces.Ocean;
import model.ocean.interfaces.OceanSpace;
import model.parameters.OceanParameters;
import model.parameters.OceanType;
import model.parameters.Vector;
import readers.implementation.XmlParametersReader;
import parsers.ParametersReader;


import java.util.List;

/**
 * creates the ocean with necessary parameters(use parameters from xml)
 */
public class XmlOceanCreator implements OceanCreator {

   private ParametersReader reader;

    @Override
    public Ocean createOcean(ParserChanger parserChanger,XmlParametersReader reader) {
      //  reader =new XmlParametersReader();
        OceanParameters oceanParameters = reader.readParams(parserChanger);
        CellsBehavior cellsBehavior = determineBehavior(oceanParameters.getOceanType());
        CellGrid cellGrid = buildCellGrid(oceanParameters.getOceanSize());
        DefaultOcean ocean =   new DefaultOcean(cellsBehavior,oceanParameters.getFlows(),cellGrid);
      //  for (Fish fish:addFishes(oceanParameters,ocean)) {
       //     ocean.addFish(fish);
      //  }
        ocean.addAllFishes(addFishes(oceanParameters,ocean));
        return ocean;
    }

    private CellsBehavior determineBehavior(OceanType oceanType){
        CellsBehavior cellsBehavior;
        switch (oceanType){
            case BORDERLESS:
                cellsBehavior=new BorderlessCellBehavior();
                break;
            case BORDERED:
                cellsBehavior=new BorderedCellBehavior();
                break;
            default:
                cellsBehavior=new BorderlessCellBehavior();
        }
        return cellsBehavior;
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

    private List<Fish> addFishes(OceanParameters parameters, OceanSpace oceanSpace){
        FishesCreator creator = new DefaultFishesCreator();
       return creator.createFishes(parameters,oceanSpace);
    }

}

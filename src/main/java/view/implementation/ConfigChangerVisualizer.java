package view.implementation;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorColorConfiguration;
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorPalette;
import configurator.implementation.DefaultParserChanger;
import model.fish.implementation.FishType;
import model.parameters.*;
import model.parameters.Rectangle;
import writers.implementation.XmlParamsPrinter;
import writers.interfaces.ParamsPrinter;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * provides GUI for changing application parameters
 */
public class ConfigChangerVisualizer {

    OceanParameters oceanParameters;
    Screen screen;
    TextGraphics textGraphics;
    WindowBasedTextGUI textGUI;

    public void visualize() {
        try {

            oceanParameters = new OceanParameters();
            Font font = new Font("Courier New", Font.BOLD, 18);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
            terminalFactory.setTerminalEmulatorFontConfiguration(new SwingTerminalFontConfiguration(true,
                    AWTTerminalFontConfiguration.BoldMode.EVERYTHING, font));
            terminalFactory.setTerminalEmulatorColorConfiguration(TerminalEmulatorColorConfiguration.newInstance(TerminalEmulatorPalette.GNOME_TERMINAL));
            screen = terminalFactory.createScreen();
            screen.startScreen();
            screen.setCursorPosition(null);
            textGUI = new MultiWindowTextGUI(screen);
            textGraphics = screen.newTextGraphics();
            pickOceanType();
            pickOceanSize();
            pickFlows();
            pickPassiveFishCount();
            pickAgressiveFishCount();
            pickFishParameters(FishType.PASSIVE);
            pickFishParameters(FishType.AGGRESSIVE);
            ParamsPrinter printer = new XmlParamsPrinter();
            printer.writeParams(oceanParameters,new DefaultParserChanger());
            screen.close();
            DefaultMenuVisualizer menuVisualizer = new DefaultMenuVisualizer();
            menuVisualizer.visualizeStartMenu();



        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private void pickOceanType() throws IOException {
        textGraphics.putString(0, 0, "Pick ocean type please");
        textGraphics.putString(0, 1, "1. Bordered");
        textGraphics.putString(0, 2, "2. Borderless");
        screen.refresh();
        KeyStroke key = screen.readInput();
        while (key == null) {
            key = screen.readInput();

        }
        switch (key.getCharacter()) {
            case '1':
                oceanParameters.setOceanType(OceanType.BORDERED);
                break;
            case '2':
                oceanParameters.setOceanType(OceanType.BORDERLESS);
                break;
            default:
                pickOceanType();
                break;
        }
    }

    private void pickFishParameters(FishType fishType) throws IOException{
      switch (fishType){
          case PASSIVE:
              String f ="for fishes";
              FishParameters fishParameters = new FishParameters();
              fishParameters.setLifeTimeTicks(enterNumber("Enter life time "+f));
              fishParameters.setStarvationTimeTicks(enterNumber("Enter starvation time "+f));
              fishParameters.setSmellSenseDistance(enterNumber("Enter smell sense distance "+f));
              fishParameters.setReproductionPeriodTicks(enterNumber("Enter reproduction period ticks "+f));
              fishParameters.setTimeToMoveThroughOneCell(enterNumber("Enter time to move through one cell "+f));
              oceanParameters.setPassiveFishParameters(fishParameters);
              break;
          case AGGRESSIVE:
              String s ="for sharks";
              FishParameters sharksParameters = new FishParameters();
              sharksParameters.setLifeTimeTicks(enterNumber("Enter life time "+s));
              sharksParameters.setStarvationTimeTicks(enterNumber("Enter starvation time "+s));
              sharksParameters.setSmellSenseDistance(enterNumber("Enter smell sense distance "+s));
              sharksParameters.setReproductionPeriodTicks(enterNumber("Enter reproduction period ticks "+s));
              sharksParameters.setTimeToMoveThroughOneCell(enterNumber("Enter time to move through one cell "+s));
              oceanParameters.setAggressiveFishParameters(sharksParameters);
              break;
      }
    }

    private void pickFlows() throws IOException{
        oceanParameters.setFlows(new ArrayList<>());
        int count = enterNumber("Enter flows count");
        for (int i=0;i<count;i++){
            pickFlowParameters(i+1);
        }
    }

    private void pickFlowParameters(int flowNumber) throws IOException{
        Flow flow = new Flow();
        flow.setRectangle(new Rectangle(
                enterNumber("Enter rectangle x coord for flow "+flowNumber),
                enterNumber("Enter rectangle y coord for flow "+flowNumber),
                enterNumber("Enter rectangle width for flow "+flowNumber),
                enterNumber("Enter rectangle height for flow "+flowNumber)
                ));
        flow.setStrength(enterNumber("Enter strength"));
        flow.setDirection(new Vector(enterNumber("Enter direction x"),enterNumber("Enter direction y")));
        oceanParameters.getFlows().add(flow);

    }

    private void pickAgressiveFishCount() throws IOException{

        oceanParameters.setAggressiveFishCount(enterNumber("Enter sharks count"));
    }

    private void pickPassiveFishCount() throws IOException{
        oceanParameters.setPassiveFishCount(enterNumber("Eneter fishes count"));
    }

    private void pickOceanSize() throws IOException {
        Vector vector = new Vector();
        screen.clear();
        pickOceanWidth(vector);
        pickOceanHeight(vector);
        oceanParameters.setOceanSize(vector);
        screen.clear();
        screen.refresh();
    }

    private void pickOceanWidth(Vector vector) throws IOException {


/*
       String result = new TextInputDialogBuilder()
                .setTitle("Width")
                .setDescription("Enter ocean width")
                .setValidationPattern(Pattern.compile("[0-9]*"), "You didn't enter a number!")
                .build()
                .showDialog(textGUI);
*/

        screen.clear();
        vector.setX(enterNumber("Enter width"));

    }


    private void pickOceanHeight(Vector vector) throws IOException {

        screen.clear();
        vector.setY(enterNumber("Enter height"));
    }

    private int enterNumber(String s) throws IOException {

        int count = 0;
        StringBuilder sb = new StringBuilder();
        textGraphics.putString(0, 0, s);
        screen.refresh();
        KeyStroke key = screen.readInput();
        while (true) {

            while (key == null) {
                key = screen.readInput();
            }
            if (key.getKeyType() == KeyType.Enter && count != 0) {
                break;
            } else {
                if (Character.isDigit(key.getCharacter())) {
                    //   for(int i=0;i<sb.length();i++){
                    //       textGraphics.putString(i,3,Character.toString(sb.charAt(i)));
                    //    }
                    textGraphics.putString(count, 3, key.getCharacter().toString());
                    sb.append(key.getCharacter());
                    count++;
                    textGraphics.putString(0, 0, s);
                    screen.refresh();
                } else {
                    if (key.getKeyType()==KeyType.Backspace&&count!=0) {
                        count--;
                        sb.delete(count,count+1);
                        textGraphics.setCharacter(count,3,' ');
                        screen.refresh();
                    }else {
                        if(key.getCharacter()=='-'&&count==0){
                            textGraphics.setCharacter(count,3,'-');
                            count++;
                            sb.append(key.getCharacter());
                            screen.refresh();
                        }
                    }

                }
            }
            key=null;
        }
        screen.clear();
        return Integer.valueOf(sb.toString());
    }


}

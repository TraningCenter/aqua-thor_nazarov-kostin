package view.implementation;


import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import controller.implementation.DefaultOceanController;

import java.awt.*;
import java.io.IOException;

/**
 * provides a main menu of application
 */
public class DefaultMenuVisualizer {

    public void visualizeStartMenu(){
        try {

            Font font = new Font("Courier New", Font.BOLD,  18);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
            terminalFactory.setTerminalEmulatorFontConfiguration(new SwingTerminalFontConfiguration(true,
                    AWTTerminalFontConfiguration.BoldMode.EVERYTHING, font));
            Screen screen = terminalFactory.createScreen();
            screen.startScreen();
            screen.setCursorPosition(null);
            TextGraphics  textGraphics=  screen.newTextGraphics();
            textGraphics.putString(0,0,"1. Start with current configuration");
            textGraphics.putString(0,1,"2. Change configuration");
            textGraphics.putString(0,2,"3. Exit");
            screen.refresh();
            inputCommand(screen);


        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void inputCommand(Screen screen) throws IOException{
        KeyStroke key = screen.readInput();//= new KeyStroke('1',false,false);
        while (key ==null){
            key=screen.readInput();

        }
        switch (key.getCharacter()){
            case '1':
                screen.close();
                DefaultOceanController controller = new DefaultOceanController();
                controller.run();
                break;
            case '2':
                screen.close();
                ConfigChangerVisualizer configChangerVisualizer = new ConfigChangerVisualizer();
                configChangerVisualizer.visualize();
                break;
            case '3':
                screen.close();
                System.exit(0);
                break;
            default:
                inputCommand(screen);
                break;
        }
    }
}

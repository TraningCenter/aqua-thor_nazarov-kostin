package view.implementation;


import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import view.interfaces.InputHandler;

import java.io.IOException;

public class DefaultInputHandler implements InputHandler ,Runnable {
    Screen screen;
    Boolean isStopped;

    DefaultInputHandler(Screen screen,Boolean isStopped){
        this.screen=screen;
        this.isStopped=isStopped;
    }

    @Override
    public void run() {

        try {

            KeyStroke key = screen.readInput();
            if(key.getKeyType()== KeyType.Escape){
                isStopped=true;
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }

    }

    public Boolean getStopped() {
        return isStopped;
    }
}

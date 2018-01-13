package view;

import org.junit.Before;
import org.junit.Test;
import view.implementation.DefaultMenuVisualizer;

public class StartMenuTest {

    private DefaultMenuVisualizer visualizer;

    //@Before
    public void init(){
         visualizer = new DefaultMenuVisualizer();
    }

    //@Test
    public void test(){
        visualizer.visualizeStartMenu();
    }

}

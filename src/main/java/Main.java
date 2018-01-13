import controller.implementation.DefaultOceanController;
import controller.implementation.MockOceanController;
import controller.interfaces.OceanController;
import view.implementation.DefaultMenuVisualizer;

import java.io.IOException;

public class Main {


    public static void main(String[] args) {
        DefaultMenuVisualizer defaultMenuVisualizer = new DefaultMenuVisualizer();
        defaultMenuVisualizer.visualizeStartMenu();


    }
}
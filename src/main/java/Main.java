import controller.implementation.MockOceanController;
import controller.interfaces.OceanController;

import java.io.IOException;

public class Main {



    public static void main(String[] args){
        try {
            OceanController oceanController = new MockOceanController();
            oceanController.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package sample;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

/**
 * Created by ibrar on 3/16/17.
 */
public class PanelLoader {

    public static String currentFxml = "";
    public static String intoFxml = "../fxml/introPanel.fxml";
    public static String mapFxml = "../fxml/mapPanel.fxml";

    private static Controller controller;

    public static ChildControllerI controllerI;

    public static void setController(Controller controll){

        controller = controll;
    }
    public static void loadPanel(){

        if(currentFxml.equals(intoFxml)){

            currentFxml = mapFxml;
            FXMLLoader loader = new FXMLLoader(PanelLoader.class.getResource(currentFxml));
            try {
                controller.setPanelHolder(loader.load());
                controllerI = loader.getController();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            currentFxml = intoFxml;
            FXMLLoader loader = new FXMLLoader(PanelLoader.class.getResource(currentFxml));

            try {
                controller.setPanelHolder(loader.load());
                controllerI = loader.getController();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

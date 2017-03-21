package sample;

import javafx.application.Application;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/mainPanel.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        PanelLoader.setController(controller);
        primaryStage.setTitle("Main Panel");
        primaryStage.setScene(new Scene(root, 815, 604));
        PanelLoader.loadPanel();
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
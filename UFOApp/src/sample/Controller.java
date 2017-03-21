package sample;

import api.ripley.Incident;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    public Button button_R;
    public Button button_L;
    public ComboBox to_combo;
    public ComboBox from_combo;
    public Pane panelHolder;
    RipleyModel ripleyModel;
    public void setPanelHolder(Node node){
        panelHolder.getChildren().setAll(node);
    }
    public void onRClick(ActionEvent actionEvent) {
        System.out.println("RClick");
        PanelLoader.loadPanel();
    }
    public void onLClick(ActionEvent actionEvent) {
        System.out.println("LClick");
        PanelLoader.loadPanel();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ripleyModel = RipleyModel.getInstance();
        for(int i= ripleyModel.getStartYear(); i<ripleyModel.getLastYear();i++){
            from_combo.getItems().add(i);
            to_combo.getItems().add(i+1);
        }
    }
    public void onBtnGoClick(ActionEvent actionEvent) {
        int fromInt = from_combo.getSelectionModel().getSelectedIndex();
        int toInt = to_combo.getSelectionModel().getSelectedIndex();
        if(fromInt>toInt){
        }else {
            if(PanelLoader.currentFxml.equals(PanelLoader.intoFxml)){
                IntroController controller = (IntroController) PanelLoader.controllerI;
                controller.label_R.setText("Getting Data .....");
            }
            String fromSt = String.valueOf( from_combo.getItems().get(fromInt));
            String toSt = String.valueOf( from_combo.getItems().get(toInt));

            ArrayList<Incident> incidents =  ripleyModel.loadRipleyData(fromSt,toSt);
            if(incidents.size()>0){
                if(PanelLoader.currentFxml.equals(PanelLoader.intoFxml)){
                    IntroController controller = (IntroController) PanelLoader.controllerI;
                    controller.Label_info.setText("Got "+incidents.size()+ " incidents.");
                }
                button_L.setDisable(false);
                button_R.setDisable(false);
            }
        }
    }
}

package sample;

import api.ripley.Incident;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by ibrar on 3/17/17.
 */
public class IncidentDetailCallView extends ListCell<Incident> {

    @FXML
    public AnchorPane incidentP;
    @FXML
    public Label incidentD;
    private FXMLLoader mLoader;

    @Override
    protected void updateItem(Incident item, boolean empty) {
        super.updateItem(item, empty);
        if(empty || item==null){
            setText(null);
            setGraphic(null);
        }else {
            if(mLoader == null){
                mLoader = new FXMLLoader(getClass().getResource("../fxml/incidentDetailView.fxml"));
                mLoader.setController(this);
                try {
                    mLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String viewS = "Time: "+item.getDateAndTime()+" City: "+item.getCity()+" Shape: "+item.getShape()+" Posted: "+item.getPosted();
                incidentD.setText(viewS);
                setText(null);
                setGraphic(incidentP);
            }
        }
    }

}

package sample;

import api.ripley.Incident;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


/**
 * Created by ibrar on 3/17/17.
 */
public class StateDetailsController implements Initializable {


    public ChoiceBox sortList;
    public ListView detailList;
    ObservableList<Incident> incidentsDetailList = FXCollections.observableArrayList();
    ObservableList<String> sortOptions = FXCollections.observableArrayList("Time","City","Shape","Posted");

    public StateDetailsController(){
        List<Incident> filtered = RipleyModel.getInstance().getIncidents().stream().filter(u -> u.getState().equals(RipleyModel.getInstance().getSelectedState())).collect(Collectors.toList());
        incidentsDetailList.addAll(filtered);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        detailList.setItems(incidentsDetailList);
        sortList.setItems(sortOptions);
        detailList.setCellFactory(itemView -> new IncidentDetailCallView());
        detailList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Incident>() {
            @Override
            public void changed(ObservableValue<? extends Incident> observable, Incident oldValue, Incident newValue) {

                RipleyModel.getInstance().setSelectedIncidentID(newValue.getIncidentID());
                Stage p  = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/message.fxml"));
                Parent root1 = null;
                try {
                    root1 = (Parent) fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                p.setTitle("Message");
                p.setScene(new Scene(root1, 361, 190));
                p.show();
            }
        });

        sortList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                switch (newValue){
                    case "Time":

                        Collections.sort(incidentsDetailList, new Comparator<Incident>() {
                            public int compare(Incident v1, Incident v2) {
                                return v1.getDateAndTime().compareTo(v2.getDateAndTime());
                            }
                        });
                        detailList.getItems().clear();
                        detailList.getItems().addAll(incidentsDetailList);
                        break;

                    case "City":

                        Collections.sort(incidentsDetailList, new Comparator<Incident>() {
                            public int compare(Incident v1, Incident v2) {
                                return v1.getCity().compareTo(v2.getCity());
                            }
                        });
                        detailList.getItems().clear();
                        detailList.setItems(incidentsDetailList);
                        break;
                    case "Shape":

                        Collections.sort(incidentsDetailList, new Comparator<Incident>() {
                            public int compare(Incident v1, Incident v2) {
                                return v1.getShape().compareTo(v2.getShape());
                            }
                        });
                        detailList.getItems().clear();
                        detailList.setItems(incidentsDetailList);
                        break;
                    case "Posted":

                        Collections.sort(incidentsDetailList, new Comparator<Incident>() {
                            public int compare(Incident v1, Incident v2) {
                                return v1.getPosted().compareTo(v2.getPosted());
                            }
                        });
                        detailList.getItems().clear();
                        detailList.setItems(incidentsDetailList);
                        break;
                }
            }
        });
    }
}

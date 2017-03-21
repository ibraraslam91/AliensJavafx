package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ibrar on 3/17/17.
 */
public class MessageController implements Initializable{

    public Label labelD;
    public Button okBtn;

    public void onOKClick(ActionEvent actionEvent) {
        labelD.setWrapText(true);
        labelD.setTextAlignment(TextAlignment.JUSTIFY);
        okBtn.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelD.setText(RipleyModel.getInstance().ripley.getIncidentDetails(RipleyModel.getInstance().getSelectedIncidentID()));
    }
}

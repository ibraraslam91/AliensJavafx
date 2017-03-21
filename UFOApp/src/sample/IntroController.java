package sample;

import api.ripley.Ripley;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by ibrar on 3/16/17.
 */
public class IntroController implements Initializable, ChildControllerI {
    public Label label_v;
    public Label label_R;
    public Label Label_info;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RipleyModel ripleyModel = RipleyModel.getInstance();
        label_v.setText(String.valueOf(ripleyModel.getVersion()));
    }
}

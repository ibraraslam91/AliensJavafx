package sample;

import api.ripley.Incident;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created by ibrar on 3/16/17.
 */
public class MapController implements Initializable, ChildControllerI {
    public HashMap<String,ImageView> stateMap = new HashMap<String,ImageView>();
    public HashMap<String,Integer> incidentsStateFreq = new HashMap<String,Integer>();
    int maxSize = -1;
    public ImageView map_Image;
    public ImageView imageV_OR;
    public ImageView imageV_CA;
    public ImageView imageV_NV;
    public ImageView imageV_ID;
    public ImageView imageV_WA;
    public ImageView imageV_MT;
    public ImageView imageV_WY;
    public ImageView imageV_UT;
    public ImageView imageV_AZ;
    public ImageView imageV_AK;
    public ImageView imageV_NM;
    public ImageView imageV_TX;
    public ImageView imageV_ND;
    public ImageView imageV_SD;
    public ImageView imageV_NE;
    public ImageView imageV_CO;
    public ImageView imageV_KS;
    public ImageView imageV_OK;
    public ImageView imageV_MN;
    public ImageView imageV_IA;
    public ImageView imageV_WI;
    public ImageView imageV_MI;
    public ImageView imageV_MO;
    public ImageView imageV_IL;
    public ImageView imageV_IN;
    public ImageView imageV_LA;
    public ImageView imageV_AR;
    public ImageView imageV_MS;
    public ImageView imageV_AL;
    public ImageView imageV_TN;
    public ImageView imageV_KY;
    public ImageView imageV_OH;
    public ImageView imageV_PA;
    public ImageView imageV_NY;
    public ImageView imageV_FL;
    public ImageView imageV_GA;
    public ImageView imageV_SC;
    public ImageView imageV_NC;
    public ImageView imageV_VA;
    public ImageView imageV_WV;
    public ImageView imageV_ME;
    public ImageView imageV_VT;
    public ImageView imageV_NH;
    public ImageView imageV_DC;
    public ImageView imageV_NJ;
    public ImageView imageV_DE;
    public ImageView imageV_CT;
    public ImageView imageV_MA;
    public ImageView imageV_RI;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<Incident> incidents = RipleyModel.getInstance().getIncidents();
        stateMap.put("OR", imageV_OR);
        stateMap.put("CA", imageV_CA);
        stateMap.put("NV", imageV_NV);
        stateMap.put("ID", imageV_ID);
        stateMap.put("WA", imageV_WA);
        stateMap.put("MT", imageV_MT);
        stateMap.put("WY", imageV_WY);
        stateMap.put("UT", imageV_UT);
        stateMap.put("AZ", imageV_AZ);
        stateMap.put("AK", imageV_AK);
        stateMap.put("NM", imageV_NM);
        stateMap.put("TX", imageV_TX);
        stateMap.put("ND", imageV_ND);
        stateMap.put("SD", imageV_SD);
        stateMap.put("NE", imageV_NE);
        stateMap.put("CO", imageV_CO);
        stateMap.put("KS", imageV_KS);
        stateMap.put("OK", imageV_OK);
        stateMap.put("MN", imageV_MN);
        stateMap.put("IA", imageV_IA);
        stateMap.put("WI", imageV_WI);
        stateMap.put("MI", imageV_MI);
        stateMap.put("MO", imageV_MO);
        stateMap.put("IL", imageV_IL);
        stateMap.put("IN", imageV_IN);
        stateMap.put("LA", imageV_LA);
        stateMap.put("AR", imageV_AR);
        stateMap.put("MS", imageV_MS);
        stateMap.put("AL", imageV_AL);
        stateMap.put("TN", imageV_TN);
        stateMap.put("KY", imageV_KY);
        stateMap.put("OH", imageV_OH);
        stateMap.put("PA", imageV_PA);
        stateMap.put("NY", imageV_NY);
        stateMap.put("FL", imageV_FL);
        stateMap.put("GA", imageV_GA);
        stateMap.put("SC", imageV_SC);
        stateMap.put("NC", imageV_NC);
        stateMap.put("VA", imageV_VA);
        stateMap.put("WV" ,imageV_WV);
        stateMap.put("ME", imageV_ME);
        stateMap.put("VT", imageV_VT);
        stateMap.put("NH", imageV_NH);
        stateMap.put("DC", imageV_DC);
        stateMap.put("NJ", imageV_NJ);
        stateMap.put("DE", imageV_DE);
        stateMap.put("CT", imageV_CT);
        stateMap.put("MA", imageV_MA);
        stateMap.put("RI", imageV_RI);
        try {
            InputStream in = new FileInputStream("map.jpg");
            InputStream ali = new FileInputStream("alien.png");
            Image aliImg = new Image(ali);
            Image img = new Image(in);
            map_Image.setImage(img);
            for(String key: stateMap.keySet()){
                stateMap.get(key).setImage(aliImg);
                List<Incident> filtered = incidents.stream().filter(u -> u.getState().equals(key)).collect(Collectors.toList());
                incidentsStateFreq.put(key,filtered.size());
                if(maxSize<filtered.size()){
                    maxSize = filtered.size();
                }
            }
            for(String key: stateMap.keySet()){
                double imageSh = ((double)incidentsStateFreq.get(key)/maxSize)*stateMap.get(key).getFitHeight()+0.1;
                double imageSw = ((double)incidentsStateFreq.get(key)/maxSize)*stateMap.get(key).getFitWidth()+0.1;
                stateMap.get(key).setFitWidth(imageSw);
                stateMap.get(key).setFitHeight(imageSh);
                System.out.println(imageSh+"  "+imageSw);
            }

            System.out.print(maxSize);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void onStateClick(String stateCode){

        RipleyModel.getInstance().setSelectedState(stateCode);
        Stage p  = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/stateDetails.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        p.setTitle(stateCode);
        p.setScene(new Scene(root1, 570, 275));
        p.show();


    }


    public void onClickedOR(MouseEvent mouseEvent) {
        onStateClick("OR");
    }

    public void onClickedCA(MouseEvent mouseEvent) {
        onStateClick("CA");

    }

    public void onClickedNV(MouseEvent mouseEvent) {
        onStateClick("NV");
    }

    public void onClickedID(MouseEvent mouseEvent) {
        onStateClick("ID");
    }

    public void onClickedWA(MouseEvent mouseEvent) {
        onStateClick("WA");
    }

    public void onClickedMT(MouseEvent mouseEvent) {
        onStateClick("MT");
    }

    public void onClickedWY(MouseEvent mouseEvent) {
        onStateClick("WY");
    }

    public void onClickedUT(MouseEvent mouseEvent) {
        onStateClick("UT");
    }

    public void onClickedAZ(MouseEvent mouseEvent) {
        onStateClick("AZ");
    }

    public void onClickedAK(MouseEvent mouseEvent) {
        onStateClick("AK");
    }

    public void onClickedNM(MouseEvent mouseEvent) {
        onStateClick("NM");
    }

    public void onClickedTX(MouseEvent mouseEvent) {
        onStateClick("TX");
    }

    public void onClickedND(MouseEvent mouseEvent) {
        onStateClick("ND");
    }

    public void onClickedSD(MouseEvent mouseEvent) {
        onStateClick("SD");
    }

    public void onClickedNE(MouseEvent mouseEvent) {
        onStateClick("NE");
    }

    public void onClickedCO(MouseEvent mouseEvent) {
        onStateClick("CO");
    }

    public void onClickedKS(MouseEvent mouseEvent) {
        onStateClick("KS");
    }

    public void onClickedOK(MouseEvent mouseEvent) {
        onStateClick("OK");
    }

    public void onClickedMN(MouseEvent mouseEvent) {
        onStateClick("MN");
    }

    public void onClickedIA(MouseEvent mouseEvent) {
        onStateClick("IA");
    }

    public void onClickedWI(MouseEvent mouseEvent) {
        onStateClick("WI");
    }

    public void onClickedMI(MouseEvent mouseEvent) {
        onStateClick("MI");
    }

    public void onClickedMO(MouseEvent mouseEvent) {
        onStateClick("MO");
    }

    public void onClickedIL(MouseEvent mouseEvent) {
        onStateClick("IL");
    }

    public void onClickedIN(MouseEvent mouseEvent) {
        onStateClick("IN");
    }

    public void onClickedLA(MouseEvent mouseEvent) {
        onStateClick("LA");
    }

    public void onClickedAR(MouseEvent mouseEvent) {
        onStateClick("AR");
    }

    public void onClickedMS(MouseEvent mouseEvent) {
        onStateClick("MS");
    }

    public void onClickedAL(MouseEvent mouseEvent) {
        onStateClick("AL");
    }

    public void onClickedTN(MouseEvent mouseEvent) {
        onStateClick("TN");
    }

    public void onClickedKY(MouseEvent mouseEvent) {
        onStateClick("KY");
    }

    public void onClickedOH(MouseEvent mouseEvent) {
        onStateClick("OH");
    }

    public void onClickedPA(MouseEvent mouseEvent) {
        onStateClick("PA");
    }

    public void onClickedNY(MouseEvent mouseEvent) {
        onStateClick("NY");
    }

    public void onClickedFL(MouseEvent mouseEvent) {
        onStateClick("FL");
    }

    public void onClickedGA(MouseEvent mouseEvent) {
        onStateClick("GA");
    }

    public void onClickedSC(MouseEvent mouseEvent) {
        onStateClick("SC");
    }

    public void onClickedNC(MouseEvent mouseEvent) {
        onStateClick("NC");
    }


    public void onClickedVA(MouseEvent mouseEvent) {
        onStateClick("VA");
    }

    public void onClickedWV(MouseEvent mouseEvent) {
        onStateClick("WV");
    }

    public void onClickedME(MouseEvent mouseEvent) {
        onStateClick("ME");
    }

    public void onClickedVT(MouseEvent mouseEvent) {
        onStateClick("VT");
    }

    public void onClickedNH(MouseEvent mouseEvent) {
        onStateClick("NH");
    }

    public void onClickedDC(MouseEvent mouseEvent) {
        onStateClick("DC");
    }

    public void onClickedNJ(MouseEvent mouseEvent) {
        onStateClick("NJ");
    }

    public void onClickedDE(MouseEvent mouseEvent) {
        onStateClick("DE");
    }

    public void onClickedCT(MouseEvent mouseEvent) {
        onStateClick("CT");
    }

    public void onClickedMA(MouseEvent mouseEvent) {
        onStateClick("MA");
    }

    public void onClickedRI(MouseEvent mouseEvent) {
        onStateClick("RI");
    }
}

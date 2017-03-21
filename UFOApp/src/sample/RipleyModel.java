package sample;

import api.ripley.Incident;
import api.ripley.Ripley;


import java.util.ArrayList;

/**
 * Created by ibrar on 3/16/17.
 */
public class RipleyModel {

    private static RipleyModel ripleyInstance;

    ArrayList<Incident> incidents;

    String selectedState;

    String selectedIncidentID ;



    public static RipleyModel getInstance(){
        if(ripleyInstance==null){
            ripleyInstance = new RipleyModel();
        }

        return ripleyInstance;
    }

    Ripley ripley;

    public RipleyModel(){
        ripley = new Ripley("90tLI3GUs9iyVD6ql2OMtA==", "lBgm4pVq9wjVqL46EnH7ew==");
    }

    public double getVersion(){
        return ripley.getVersion();
    }

    public String getLateUpdate(){
        return ripley.getLastUpdated();
    }

    public int getStartYear(){
        return ripley.getStartYear();
    }

    public int getLastYear(){
        return ripley.getLatestYear();
    }

    public ArrayList<Incident> loadRipleyData(String startYear,String endYear){

        incidents = ripley.getIncidentsInRange(startYear+"-01-01 00:00:00",endYear+"-01-01 00:00:00");
        for(Incident incident : incidents){
            System.out.println(incident.getState());
        }
        return incidents;
    }

    public ArrayList<Incident> getIncidents() {
        return incidents;
    }

    public String getSelectedState() {
        return selectedState;
    }

    public void setSelectedState(String selectedState) {
        this.selectedState = selectedState;
    }

    public String getSelectedIncidentID() {
        return selectedIncidentID;
    }

    public void setSelectedIncidentID(String selectedIncidentID) {
        this.selectedIncidentID = selectedIncidentID;
    }
}

package output_data;

import java.util.ArrayList;

public class LocalEventOutputData {
    private ArrayList<Object> insatance_atribute;
    private int eventID;
    private String event_name;
    public  LocalEventOutputData(ArrayList<Object> insatance_atribute, int eventID, String event_name){
        this.eventID = eventID;
        this.event_name = event_name;
        this.insatance_atribute = insatance_atribute;
    }

    public ArrayList<Object> getInsatance_atribute(){
        return(this.insatance_atribute);
    }
    public int getEventID(){
        return(this.eventID);
    }
    public  String getEvent_name(){
        return (this.event_name);
    }

}

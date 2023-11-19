package OutputData;

import java.util.ArrayList;

public class NotificationOutputData {
    private ArrayList<Object> insatance_atribute;
    private int notificationID;
    private String notification_name;
    public NotificationOutputData(ArrayList<Object> insatance_atribute, int notificationID, String notification_name){
        this.notificationID = notificationID;
        this.notification_name = notification_name;
        this.insatance_atribute = insatance_atribute;
    }

    public ArrayList<Object> getInsatance_atribute(){
        return(this.insatance_atribute);
    }
    public int getnotificationID(){
        return(this.notificationID);
    }
    public  String getnotification_name(){
        return (this.notification_name);
    }
}

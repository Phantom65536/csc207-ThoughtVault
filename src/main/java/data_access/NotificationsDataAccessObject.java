package data_access;

import entity.Notifications;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import use_case.NotificationsDataAccessInterface;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class NotificationsDataAccessObject implements NotificationsDataAccessInterface {
    private final Map<Integer, Notifications> notifications = new HashMap<>();
    private final File jsonFile;

    @SuppressWarnings("unchecked")
    public NotificationsDataAccessObject(String jsonPath) throws IOException, ParseException {
        jsonFile = new File(jsonPath);

        if (jsonFile.length() == 0) {
            // Create new file with jsonPath if it doesn't exist
            save();
        } else {
            // Read existing Events JSON file and map each event JSON object to a key-value pair in notifications
            JSONParser jsonParser = new JSONParser();
            try (FileReader reader = new FileReader(jsonFile)) {
                Object obj = jsonParser.parse(reader);
                JSONArray eventsList = (JSONArray) obj;
                eventsList.forEach(notif -> parseNotiObj((JSONObject) notif));
            }
        }
    }

    private void parseNotiObj(JSONObject notifJSON) {
        int eventID = ((Long) notifJSON.get("eventid")).intValue();
        JSONArray occurJSONArr = (JSONArray) notifJSON.get("occurrences");
        ArrayList<LocalDateTime> occurList = new ArrayList<>();
        for (String occurrence : (ArrayList<String>) occurJSONArr)
            occurList.add(LocalDateTime.parse(occurrence));

        notifications.put(eventID, new Notifications(eventID, occurList));
    }

    public Notifications getByID(int eventID) {
        return notifications.get(eventID);
    }

    public void save(Notifications notif) {
        notifications.put(notif.getEventID(), notif);
        save();
    }
    
    public void delete(int eventID) {
        if (notifications.remove(eventID) != null)
            save();
    }

    @SuppressWarnings("unchecked")
    private void save() {
        try (FileWriter writer = new FileWriter(jsonFile)) {
            // Write every event in the events map to jsonFile
            JSONArray allNotifJSON = new JSONArray();
            for (int id : notifications.keySet()) {
                JSONObject notifDetails = new JSONObject();
                notifDetails.put("eventid", id);

                JSONArray occursJSON = new JSONArray();
                ArrayList<LocalDateTime> occurs = notifications.get(id).getOccurrences();
                for (LocalDateTime occur : occurs)
                    occursJSON.add(occur.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                notifDetails.put("occurrences", occursJSON);

                allNotifJSON.add(notifDetails);
            }

            writer.write(allNotifJSON.toJSONString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException((e));
        }
    }

    // for testing only
    public static void main(String[] args) throws IOException, ParseException {
        NotificationsDataAccessObject dao = new NotificationsDataAccessObject("./testNotifications.json");

        Notifications notif1 = new Notifications(1, new ArrayList<>());
        dao.save(notif1);
        dao.save(new Notifications(2, new ArrayList<>(Arrays.asList(LocalDateTime.parse("2023-09-21T14:15"),
                                                                            LocalDateTime.parse("2023-09-22T16:12")))));

        notif1.setOccurrences(new ArrayList<>(List.of(LocalDateTime.parse("2020-02-21T03:55"))));
        dao.save(notif1);

        dao.delete(notif1.getEventID());
    }
}

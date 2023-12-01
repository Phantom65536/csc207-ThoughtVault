package data_access;

import entity.Event;
import entity.NoteInterface;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import use_case.EventsDataAccessInterface;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NoteDataAccessObject implements NoteDataAccessInterface {
    private final Map<String, NoteInterface> notes = new HashMap<>();
    private final File jsonFile;
    private int lastID = 0;

    @SuppressWarnings("unchecked")
    public NoteDataAccessObject(String jsonPath) throws IOException, ParseException {
        jsonFile = new File(jsonPath);

        if (jsonFile.length() == 0) {
            // Create new file with jsonPath if it doesn't exist
            save();
        } else {
            // Read existing Events JSON file and map each event JSON object to a key-value pair in events
            JSONParser jsonParser = new JSONParser();
            try (FileReader reader = new FileReader(jsonFile)) {
                Object obj = jsonParser.parse(reader);
                JSONArray eventsList = (JSONArray) obj;
                eventsList.forEach(evt -> parseEventObj((JSONObject) evt));
            }
        }
    }

    private void parseEventObj(JSONObject eventJSON) {
        int eventID = ((Long) eventJSON.get("id")).intValue();
        String title = (String) eventJSON.get("title");
        int userID = ((Long) eventJSON.get("userid")).intValue();
        LocalDate date = LocalDate.parse((String) eventJSON.get("date"));
        LocalTime startTime = LocalTime.parse((String) eventJSON.get("starttime"));
        LocalTime endTime = LocalTime.parse((String) eventJSON.get("endtime"));
        String location = (String) eventJSON.get("location");
        String description = (String) eventJSON.get("description");
        boolean labelIsWork = (eventJSON.get("label")).equals("WORK");
        Boolean pinned = (Boolean) eventJSON.get("pinned");

        JSONArray eventsJSONArr = (JSONArray) eventJSON.get("sub-events");
        ArrayList<Integer> eventsList = new ArrayList<>();
        for (Long eventLongID : (ArrayList<Long>) eventsJSONArr)
            eventsList.add(eventLongID.intValue());

        events.put(eventID, new Event(title, userID, date, startTime, endTime, location, description, labelIsWork, pinned, eventsList));
        lastID = eventID;
    }

    public NoteInterface getByTitle(String title) {
        return notes.get(title);
    }

    // Get all events associated with a user
    public ArrayList<Event> getAllUserEvents(int userID) {
        ArrayList<Event> userEventsID = new ArrayList<>();
        for (int id : events.keySet())
            if (events.get(id).getUserID() == userID)
                userEventsID.add(events.get(id));
        return userEventsID;
    }

    public int getNewID() {
        return ++lastID;
    }

    public void save(Event event) {
        events.put(event.getID(), event);
        save();
    }

    public void delete(int eventID) {
        Event rmEvent = events.remove(eventID);
        if (rmEvent == null)
            return;
        // If this event is a child of any other event, remove this event's id in its parent's sub-events
        for (int id : events.keySet()) {
            int count = 0;
            for (int subEventID : events.get(id).getSubEvents()) {
                if (subEventID == eventID) {
                    events.get(id).removeSubEvents(count);
                    break;
                }
                count++;
            }
        }
        save();
    }

    @SuppressWarnings("unchecked")
    private void save() {
        try (FileWriter writer = new FileWriter(jsonFile)) {
            // Write every event in the events map to jsonFile
            JSONArray allEventsJSON = new JSONArray();
            for (int id : events.keySet()) {
                JSONObject eventDetails = new JSONObject();
                eventDetails.put("id", events.get(id).getID());
                eventDetails.put("title", events.get(id).getTitle());
                eventDetails.put("userid", events.get(id).getUserID());
                eventDetails.put("date", events.get(id).getDate().format(DateTimeFormatter.ISO_LOCAL_DATE));

                LocalTime[] startEndTimes = events.get(id).getStartEndTIme();
                eventDetails.put("starttime", startEndTimes[0].format(DateTimeFormatter.ISO_LOCAL_TIME));
                eventDetails.put("endtime", startEndTimes[1].format(DateTimeFormatter.ISO_LOCAL_TIME));

                eventDetails.put("location", events.get(id).getLocation());
                eventDetails.put("description", events.get(id).getDescription());
                eventDetails.put("label", events.get(id).isWork() ? "WORK" : "PERSONAL");
                eventDetails.put("pinned", events.get(id).getPinned());

                JSONArray subEventsJSON = new JSONArray();
                subEventsJSON.addAll(events.get(id).getSubEvents());
                eventDetails.put("sub-events", subEventsJSON);

                allEventsJSON.add(eventDetails);
            }

            writer.write(allEventsJSON.toJSONString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException((e));
        }
    }

    // for testing only
    public static void main(String[] args) throws IOException, ParseException {
        NoteDataAccessObject dao = new NoteDataAccessObject("./testEvent.json");

        Event firstEvent = new Event(dao.getNewID(), "first", 0, LocalDate.parse("2023-11-09"), LocalTime.NOON, LocalTime.parse("20:00"),
                "TA guy's crib", "This is a description.", true, false, new ArrayList<>());
        dao.save(firstEvent);
        dao.save(new Event(dao.getNewID(), "second", 0, LocalDate.parse("2022-01-02"), LocalTime.MIDNIGHT, LocalTime.parse("11:00"),
                "TA guy's toilet", "There is no way this is not a description.", true, true, new ArrayList<>(Arrays.asList(firstEvent.getID(), 1000))));
        dao.save(new Event(dao.getNewID(), "third", 0, LocalDate.parse("2022-01-02"), LocalTime.MIDNIGHT, LocalTime.parse("23:59"),
                "Garbage chute :)))", "NO DESCRIPTION T_T", false, true, new ArrayList<>(Arrays.asList(0, firstEvent.getID()))));

        Event otherUserEvent = new Event(dao.getNewID(), "another user", 1, LocalDate.parse("2022-01-02"), LocalTime.MIDNIGHT, LocalTime.parse("23:59"),
                "Garbage chute :)))", "NO DESCRIPTION T_T", false, true, new ArrayList<>());
        dao.save(otherUserEvent);

        otherUserEvent.amendAllAttributes("another user editted", LocalDate.parse("2022-01-02"), LocalTime.MIDNIGHT, LocalTime.parse("23:59"),
                "Garbage chute :(((", "EDITED DESCRIPTION T_T", true, true, new ArrayList<>());
        dao.save(otherUserEvent);

        ArrayList<Event> user0Events = dao.getAllUserEvents(0);
        dao.delete(firstEvent.getID());
    }
}

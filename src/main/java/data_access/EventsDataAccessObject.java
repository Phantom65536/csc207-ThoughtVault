package data_access;

import entity.LocalEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A concrete class inheriting EntriesDAO and implementing EventsDAO by saving events as JSON entries
 */
public class EventsDataAccessObject extends EntriesDataAccessObject<LocalEvent> {

    public EventsDataAccessObject(String jsonPath) throws IOException, ParseException {
        super(jsonPath);
    }

    void parseEntryObj(JSONObject entryJSON) {
        LocalDate date = LocalDate.parse((String) entryJSON.get("date"));
        LocalTime startTime = LocalTime.parse((String) entryJSON.get("starttime"));
        LocalTime endTime = LocalTime.parse((String) entryJSON.get("endtime"));

        JSONArray eventsJSONArr = (JSONArray) entryJSON.get("sub-events");
        ArrayList<Integer> eventsList = parseSubEntries(eventsJSONArr);

        int eventID = parseEntryID(entryJSON);
        entries.put(eventID, new LocalEvent(eventID, parseTitle(entryJSON), parseUserID(entryJSON), date, startTime, endTime,
                parseLocation(entryJSON), parseDescription(entryJSON), parseLabel(entryJSON), parsePinned(entryJSON), eventsList));
        lastID = eventID;
    }

    @SuppressWarnings("unchecked")
    void save() {
        try (FileWriter writer = new FileWriter(jsonFile)) {
            // Write every event in the events map to jsonFile
            JSONArray allEventsJSON = new JSONArray();
            for (int id : entries.keySet()) {
                JSONObject eventDetails = putEntryDetails(id);

                eventDetails.put("date", entries.get(id).getDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
                LocalTime[] startEndTimes = entries.get(id).getStartEndTIme();
                eventDetails.put("starttime", startEndTimes[0].format(DateTimeFormatter.ISO_LOCAL_TIME));
                eventDetails.put("endtime", startEndTimes[1].format(DateTimeFormatter.ISO_LOCAL_TIME));

                JSONArray subEventsJSON = new JSONArray();
                subEventsJSON.addAll(entries.get(id).getDescendants());
                eventDetails.put("sub-events", subEventsJSON);

                allEventsJSON.add(eventDetails);
            }
            writer.write(allEventsJSON.toJSONString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException((e));
        }
    }
}

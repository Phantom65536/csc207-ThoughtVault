package data_access;

import entity.Note;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A concrete class inheriting EntriesDAO and implementing NotesDAO by saving notes as JSON entries
 */
public class NotesDataAccessObject extends EntriesDataAccessObject<Note> {
    public NotesDataAccessObject(String jsonFile) throws IOException, ParseException {
        super(jsonFile);
    }

    void parseEntryObj(JSONObject entryJSON) {
        JSONArray notesJSONArr = (JSONArray) entryJSON.get("sub-notes");
        ArrayList<Integer> notesList = parseSubEntries(notesJSONArr);

        int noteID = parseEntryID(entryJSON);
        entries.put(noteID, new Note(noteID, parseTitle(entryJSON), parseUserID(entryJSON), parseLocation(entryJSON),
                parseDescription(entryJSON), parseLabel(entryJSON), parsePinned(entryJSON), notesList));
        lastID = noteID;
    }

    @SuppressWarnings("unchecked")
    void save() {
        try (FileWriter writer = new FileWriter(jsonFile)) {
            // Write every entry in the entries map to jsonFile
            JSONArray allEntriesJSON = new JSONArray();
            for (int id : entries.keySet()) {
                JSONObject entryDetails = putEntryDetails(id);

                JSONArray subNotesJSON = new JSONArray();
                subNotesJSON.addAll(entries.get(id).getDescendants());
                entryDetails.put("sub-notes", subNotesJSON);

                allEntriesJSON.add(entryDetails);
            }
            writer.write(allEntriesJSON.toJSONString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException((e));
        }
    }
}

package interface_adapter.note;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is a State for the NoteView.
 */
public class NoteState {
    private int userId;


    private String title;

    private String location;

    private String description;

    private boolean isWork;

    private boolean pinned;

    private int id;

    private ArrayList<Integer> subEntries;
    private HashMap<Integer, String> allEntries;

    public NoteState() {
    }

    /**
     * Constructor for NoteState.
     *
     * @param id            The note's ID.
     * @param title         The title of the note.
     * @param location      The location of the note.
     * @param description   The description of the note.
     * @param isWork        Whether the note is for work.
     * @param pinned        Whether the note is pinned.
     * @param subEntries     The sub entries of the note.
     * @param userId        The note creator's ID
     */
    public NoteState(int id, String title, String location, String description, boolean
            isWork, boolean pinned, ArrayList<Integer> subEntries, int userId, HashMap<Integer, String> allEntries) {
        this.title = title;

        this.id = id;

        this.location = location;

        this.description = description;

        this.isWork = isWork;

        this.pinned = pinned;

        this.subEntries = subEntries;

        this.userId = userId;
        this.allEntries = allEntries;
    }

    /**
     * Getter for the title of the note.
     *
     * @return The title of the note.
     */

    public String getTitle() {
        return title;
    }

    /**
     * Getter for the location of the note.
     *
     * @return The location of the note.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Getter for the description of the note.
     *
     * @return The description of the note.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for whether the note is for work.
     *
     * @return Whether the note is for work.
     */
    public boolean getIsWork() {
        return this.isWork;
    }

    /**
     * Getter for whether the note is pinned.
     *
     * @return Whether the note is pinned.
     */
    public boolean getPinned() {
        return this.pinned;
    }

    /**
     * Getter for the sub events of the note.
     *
     * @return The sub events of the note.
     */
    public ArrayList<Integer> getSubEntries() {
        return subEntries;
    }

    public HashMap<Integer, String> getAllEntries() {
        return allEntries;
    }

    public int getUserId(){return this.userId;}
    public int getId(){return this.id;}
    public void setUserId(int userId){this.userId = userId;}
    public void addEntryId(int entryId) { if (!subEntries.contains(entryId)) this.subEntries.add(entryId); };
}

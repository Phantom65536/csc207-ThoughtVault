package interface_adapter.note;

import java.lang.reflect.Array;
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

    private ArrayList<Integer> subNotes;
    private HashMap<Integer, String> allNotes;

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
     * @param subNotes     The sub events of the note.
     * @param userId        The note creator's ID
     */
    public NoteState(int id, String title, String location, String description, boolean
            isWork, boolean pinned, ArrayList<Integer> subNotes, int userId, HashMap<Integer, String> allNotes) {
        this.title = title;

        this.id = id;

        this.location = location;

        this.description = description;

        this.isWork = isWork;

        this.pinned = pinned;

        this.subNotes = subNotes;

        this.userId = userId;
        this.allNotes = allNotes;
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
    public ArrayList<Integer> getSubNotes() {
        return subNotes;
    }

    public int getUserId(){return this.userId;}
    public int getId(){return this.id;}
    public void setUserId(int userId){this.userId = userId;}

    public HashMap<Integer, String> getAllNotes() {
        return allNotes;
    }
}

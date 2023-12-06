package interface_adapter.note;

import java.util.ArrayList;

/**
 * This class is a State for the NoteView.
 */
public class NoteState {
    private String title;

    private String location;

    private String description;

    private boolean isWork;

    private boolean pinned;

    private ArrayList<Integer> subEvents;

    public NoteState() {
    }

    /**
     * Constructor for NoteState.
     *
     * @param title         The title of the note.
     * @param location      The location of the note.
     * @param description   The description of the note.
     * @param isWork        Whether the note is for work.
     * @param pinned        Whether the note is pinned.
     * @param subEvents     The sub events of the note.
     */
    public NoteState(String title, String location, String description, boolean
            isWork, boolean pinned, ArrayList<Integer> subEvents) {
        this.title = title;

        this.location = location;

        this.description = description;

        this.isWork = isWork;

        this.pinned = pinned;

        this.subEvents = subEvents;
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
    public ArrayList<Integer> getSubEvents() {
        return subEvents;
    }
}

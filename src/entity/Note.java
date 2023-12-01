package entity;

import java.time.LocalDate;

public class Note implements NoteInterface {
    private String title;
    private final String userID;
    private LocalDate date;
    private String description;
    public enum labelCat {WORK, PERSONAL};
    private Note.labelCat label;
    private boolean pinned;

    public Note(String title, String userID, LocalDate date, String description,
                boolean isWork, boolean pinned) {
        this.title = title;
        this.userID = userID;
        this.title = title;
        this.date = date;
        this.description = description;
        this.pinned = pinned;
        label = isWork ? Note.labelCat.WORK : Note.labelCat.PERSONAL;
    }

    public String getTitle() { return title; }

    public String getUserID() { return userID; }

    public LocalDate getDate() { return date; }

    public String getDescription() { return description; }

    public boolean isWork() { return label == Note.labelCat.WORK; }

    public boolean getPinned() { return pinned; }
}

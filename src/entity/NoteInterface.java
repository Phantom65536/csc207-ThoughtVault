package entity;

import java.time.LocalDate;

public interface NoteInterface {
    String getTitle();
    String getUserID();
    LocalDate getDate();
    String getDescription();
    boolean isWork();
    boolean getPinned();
}

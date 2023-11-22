package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public interface LocalEventInterface extends NoteInterface {
    LocalDate getDate();
    LocalTime[] getStartEndTIme();
}

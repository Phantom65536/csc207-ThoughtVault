package InputData;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventInputData {
    private int userid;
    private String username;
    private String password;
    private String credential;

    private LocalDate date;
    private LocalTime startTime = LocalTime.MIDNIGHT;                 // default starttime when user specifies whole-day
    private LocalTime endTime = LocalTime.of(23, 59);
    public EventInputData (int userid, String username, String password, String credential, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.credential = credential;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

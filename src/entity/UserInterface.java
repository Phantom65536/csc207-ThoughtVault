package entity;

import java.util.Calendar;

public interface UserInterface {
    String getUserID();
    Calendar getCalendar();
    CredentialsInterface getCredentials();
}

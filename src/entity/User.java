package entity;

import java.util.Calendar;

public class User implements UserInterface {
    private final String userID;
    private Calendar calendar;
    private CredentialsInterface credentials;

    public User(String userID, Calendar calendar,
                CredentialsInterface credentials) {
        this.userID = userID;
        this.calendar = calendar;
        this.credentials = credentials;
    }

    public String getUserID() {
        return userID;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public CredentialsInterface getCredentials() {
        return credentials;
    }
}

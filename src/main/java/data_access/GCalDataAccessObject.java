package data_access;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;
import use_case.gcalevent.GCalEventDataAccessInterface;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class GCalDataAccessObject implements GCalEventDataAccessInterface {
    private Calendar calendar = null;
    private String calendarId = null;
    private static final String APPLICATION_NAME = "Thought Vault";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final List<String> SCOPES =
            Collections.singletonList(CalendarScopes.CALENDAR);

    public GCalDataAccessObject() {}

    /**
     Assume that there is only ONE calendar.
     Set the user's calendar and calendarID given a Credential object.
     **/
    public void setUserCalendar(Credential credential) throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        Calendar service =
                new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                        .setApplicationName(APPLICATION_NAME)
                        .build();

        this.calendar = service;

        CalendarList calendarList = service.calendarList().list().setPageToken(null).execute();
        List<CalendarListEntry> items = calendarList.getItems();
        this.calendarId = items.get(0).getId();

    }

    /**
     * Reset the user's calendar
     * */
    public void resetUserCalendar() {
        calendar = null;
        calendarId = null;
    }

    /**
     * Returns the calendar associated with the GCalDataAccessObject instance.
     * */
    public Calendar getCalendar() {
        return calendar;
    }

    /**
     * Returns the unique identifier (ID) associated with the calendar.
     * */
    public String getCalendarId() {
        return calendarId;
    }

    /**
     * Checks if an event with the specified event ID exists in the associated calendar.
     * @throws IOException if the event cannot be retrieved from the user's Google calendar.
     * */
    public boolean eventExists(String eventId) throws IOException {
        if (calendar == null)
            return false;
        return calendar.events().get(calendarId, eventId).execute().getId() != null;
    }

    /**
     * Creates an authorized Credential object.
     *
     * @param jsonCredentials The contents of credentials.json.
     * @return An authorized Credential object.
     */
    public static Credential getCredentials(String jsonCredentials) {
        try {
            GoogleClientSecrets clientSecrets =
                    GoogleClientSecrets.load(JSON_FACTORY, new StringReader(jsonCredentials));

            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

            // Build flow and trigger user authorization request.
            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                    HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                    .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                    .setAccessType("offline")
                    .build();

            LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
            Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
            return credential;
        } catch (IOException | GeneralSecurityException e) {
            return null;
        }
    }

}

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.Value;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;
import data_access.GCalDataAccessObject;
import use_case.gcalevent.GCalEventDataAccessInterface;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/* class to demonstrate use of Calendar events list API */
public class CalendarQuickstart {
    /**
     * Application name.
     */
    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    /**
     * Directory to store authorization tokens for this application.
     */
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES =
            Collections.singletonList(CalendarScopes.CALENDAR_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    @Value("{\"installed\":{\"client_id\":\"795633948902-lnv99a5r05977jor1cm9harbqhq4kcv2.apps.googleusercontent.com\",\"project_id\":\"thought-vault-400118\",\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"token_uri\":\"https://oauth2.googleapis.com/token\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\",\"client_secret\":\"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\",\"redirect_uris\":[\"http://localhost\"]}}")
    private static String testApiKey;

    static JList b;
    static DefaultListModel<String> eventsListModel;

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
//    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
//            throws IOException {
//        // Load client secrets.
//        InputStream in = CalendarQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
//        if (in == null) {
//            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
//        }
//        GoogleClientSecrets clientSecrets =
//                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
//
//        // Build flow and trigger user authorization request.
//        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
//                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
//                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
//                .setAccessType("offline")
//                .build();
//        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
//        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
//        //returns an authorized Credential object.
//        return credential;
//    }
    public static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT, String jsonCredentials)
            throws IOException {
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new StringReader(jsonCredentials));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        //returns an authorized Credential object.
        return credential;
    }

    public static void main(String... args) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
//        System.out.println(testApiKey);
        // String APIkey = "{\"installed\":{\"client_id\":\"676658923300-jefh7ko5cp9n7cf92vj427ltrd0rumo4.apps.googleusercontent.com\",\"project_id\":\"thought-vault-400423\",\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"token_uri\":\"https://oauth2.googleapis.com/token\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\",\"client_secret\":\"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\",\"redirect_uris\":[\"http://localhost\"]}}";
//        String apiKey = System.getProperty("API_KEY");
//        System.out.println(apiKey);
        Properties prop = new Properties();
        InputStream input = null;
        input = new FileInputStream("gradle.properties");

        // load a properties file
        prop.load(input);

        // get the property value and print it out
        System.out.println(prop.getProperty("apiKey"));
        GCalEventDataAccessInterface userDataAccessObject = new GCalDataAccessObject();
        String APIkey = prop.getProperty("apiKey");
        // Credential credential = getCredentials(APIkey);
//        System.out.println(credential);
        Calendar service =
                new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT, prop.getProperty("apiKey")))
                        .setApplicationName(APPLICATION_NAME)
                        .build();

//        String APIkey = "{\"installed\":{\"client_id\":\"676658923300-jefh7ko5cp9n7cf92vj427ltrd0rumo4.apps.googleusercontent.com\",\"project_id\":\"thought-vault-400423\",\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"token_uri\":\"https://oauth2.googleapis.com/token\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\",\"client_secret\":\"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\",\"redirect_uris\":[\"http://localhost\"]}}";
//
//        Calendar service =
//                new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT, APIkey))
//                        .setApplicationName(APPLICATION_NAME)
//                        .build();

//        CalendarList calendarList = service.calendarList().list().setPageToken(null).execute();
//        List<CalendarListEntry> items = calendarList.getItems();
//        System.out.println(items.get(0).getId());

//        DateTime now = new DateTime(System.currentTimeMillis());
//        Events events = service.events().list("primary")
//                .setMaxResults(10)
//                .setTimeMin(now)
//                .setOrderBy("startTime")
//                .setSingleEvents(true)
//                .execute();
//        List<Event> items = events.getItems();
//
//        // The main application window.
//        JFrame application = new JFrame("ThoughtVault Example");
//        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        JPanel p =new JPanel();
//
//        eventsListModel = new DefaultListModel<String>();
//        for (Event event : items) {
//            eventsListModel.addElement(event.getSummary()); // Assuming 'getSummary()' returns the event title
//        }
////        eventsListModel.addAll(items);
//        b = new JList<>(eventsListModel);
//        b.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//
////        CalendarQuickstart c = new CalendarQuickstart();
//        b.addListSelectionListener(
//                new ListSelectionListener() {
//                    @Override
//                    public void valueChanged(ListSelectionEvent e) {
//                        int selectedIndex = b.getSelectedIndex();
//                        System.out.println(selectedIndex);
//                    }
//                }
//        );
//
//        p.add(b);
//
//        application.add(p);
//
//
//        application.setSize(400,400);
//        application.show();

        // List the next 10 events from the primary calendar.
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("primary")
                .setMaxResults(10)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            System.out.println("Upcoming events");
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                System.out.printf("%s (%s)\n", event.getSummary(), start);
                System.out.println(event.getId());
            }
        }
    }
}
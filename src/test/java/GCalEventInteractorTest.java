
import data_access.EventsDataAccessObject;
import data_access.GCalDataAccessObject;
import entity.LocalEvent;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;
import use_case.gcalevent.*;
import use_case.local_event.EntriesDataAccessInterface;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class GCalEventInteractorTest {


    @Test
    public void importEvent() throws IOException, GeneralSecurityException, ParseException {
        // Read the apiKey environment variable
        Properties prop = new Properties();
        InputStream input = null;
        input = new FileInputStream("gradle.properties");
        prop.load(input);
        String APIkey = prop.getProperty("apiKey");

        GCalEventDataAccessInterface userDataAccessObject = new GCalDataAccessObject(APIkey);
        EntriesDataAccessInterface entriesDataAccessObject = new EventsDataAccessObject("./testEvent.json");
        String eventId = "3ddfaspndfpa8qn96drjds2r92";

        // This creates a successPresenter that tests whether the test case is as we expect.
        GCalEventOutputBoundary successPresenter = new GCalEventOutputBoundary() {
            @Override
            public void prepareSuccessView(GCalEventOutputData evt) {
                Assert.assertEquals("first event", evt.getTitle()); // check that event title matches
                Assert.assertNotNull(evt.getDate()); // check that there's a start time.
            }

            @Override
            public void prepareFailView(String error) {
                Assert.fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToHome() {
                Assert.fail("Use case failure is unexpected.");
            }
        };

        GCalEventInputBoundary interactor = new GCalEventInteractor(userDataAccessObject, successPresenter, entriesDataAccessObject);
        interactor.importEvent(eventId);
    }

    @Test
    public void exportEvent() throws GeneralSecurityException, IOException, ParseException {
        // Read the apiKey environment variable
        Properties prop = new Properties();
        InputStream input = null;
        input = new FileInputStream("gradle.properties");
        prop.load(input);
        String APIkey = prop.getProperty("apiKey");

        GCalEventDataAccessInterface userDataAccessObject = new GCalDataAccessObject(APIkey);
        // EntriesDataAccessInterface entriesDataAccessObject = new EventsDataAccessObject("./testEvent.json");
        EventsDataAccessObject dao = new EventsDataAccessObject("./testEvent.json");
        LocalEvent firstEvent = new LocalEvent(dao.getNewID(), "first", 0, LocalDate.parse("2023-11-09"), LocalTime.NOON, LocalTime.parse("20:00"),
                "TA guy's crib", "This is a description.", true, false, new ArrayList<>());
        dao.save(firstEvent);
        LocalEvent secondEvent = new LocalEvent(dao.getNewID(), "second", 0, LocalDate.parse("2022-01-02"), LocalTime.MIDNIGHT, LocalTime.parse("11:00"),
                "TA guy's toilet", "There is no way this is not a description.", true, true, new ArrayList<>(Arrays.asList(firstEvent.getID(), 1000)));
        dao.save(secondEvent);
        GCalEventOutputBoundary successPresenter = new GCalEventOutputBoundary() {
            @Override
            public void prepareSuccessView(GCalEventOutputData evt) {
                Assert.assertEquals("second", evt.getTitle()); // check that event title matches
                Assert.assertEquals("TA guy's toilet", evt.getLocation()); // check that event location matches
            }

            @Override
            public void prepareFailView(String error) {
                Assert.fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToHome() {
                Assert.fail("Use case failure is unexpected.");
            }
        };
        GCalEventInputBoundary interactor = new GCalEventInteractor(userDataAccessObject, successPresenter, dao);
        interactor.exportEvent(secondEvent.getID());
    }

    @Test
    public void getAllEvents() throws GeneralSecurityException, IOException, ParseException {
        // Read the apiKey environment variable
        Properties prop = new Properties();
        InputStream input = null;
        input = new FileInputStream("gradle.properties");
        prop.load(input);
        String APIkey = prop.getProperty("apiKey");

        GCalEventDataAccessInterface userDataAccessObject = new GCalDataAccessObject(APIkey);
        EntriesDataAccessInterface entriesDataAccessObject = new EventsDataAccessObject("./testEvent.json");
        GCalEventOutputBoundary successPresenter = new GCalEventOutputBoundary() {
            @Override
            public void prepareSuccessView(GCalEventOutputData evt) {
                Assert.assertEquals("first event", evt.getTitle()); // check that event title matches
                Assert.assertNotNull(evt.getStartTime()); // check that there's a start time.
            }

            @Override
            public void prepareFailView(String error) {
                Assert.fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToHome() {
                Assert.fail("Use case failure is unexpected.");
            }
        };

        GCalEventInputBoundary interactor = new GCalEventInteractor(userDataAccessObject, successPresenter, entriesDataAccessObject);
        ArrayList<GCalEventInputData> listOfEvents = interactor.getAllEvents();
        Assert.assertEquals("test event", listOfEvents.get(0).getTitle());
    }

    @Test
    public void switchToHome() {
    }
}
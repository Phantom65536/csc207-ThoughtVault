
import data_access.EventsDataAccessObject;
import data_access.GCalDataAccessObject;
import entity.LocalEvent;
import junit.framework.TestCase;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;
import use_case.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class GCalEventInteractorTest {

    @Test
    public void importEvent() throws IOException, GeneralSecurityException, ParseException {

        String APIkey = "{\"installed\":{\"client_id\":\"676658923300-jefh7ko5cp9n7cf92vj427ltrd0rumo4.apps.googleusercontent.com\",\"project_id\":\"thought-vault-400423\",\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"token_uri\":\"https://oauth2.googleapis.com/token\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\",\"client_secret\":\"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\",\"redirect_uris\":[\"http://localhost\"]}}";
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
        String APIkey = "{\"installed\":{\"client_id\":\"676658923300-jefh7ko5cp9n7cf92vj427ltrd0rumo4.apps.googleusercontent.com\",\"project_id\":\"thought-vault-400423\",\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"token_uri\":\"https://oauth2.googleapis.com/token\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\",\"client_secret\":\"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\",\"redirect_uris\":[\"http://localhost\"]}}";
        GCalEventDataAccessInterface userDataAccessObject = new GCalDataAccessObject(APIkey);
        // EntriesDataAccessInterface entriesDataAccessObject = new EventsDataAccessObject("./testEvent.json");
        EventsDataAccessObject dao = new EventsDataAccessObject("./testEvent.json");
        LocalEvent firstEvent = new LocalEvent(dao.getNewID(), "first", 0, LocalDate.parse("2023-11-09"), LocalTime.NOON, LocalTime.parse("20:00"),
                "TA guy's crib", "This is a description.", true, false, new ArrayList<>());
        dao.save(firstEvent);
        GCalEventOutputBoundary successPresenter = new GCalEventOutputBoundary() {
            @Override
            public void prepareSuccessView(GCalEventOutputData evt) {
                Assert.assertEquals("first", evt.getTitle()); // check that event title matches
                Assert.assertEquals("TA guy's crib", evt.getLocation()); // check that event location matches
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
        interactor.exportEvent(firstEvent.getID());
    }

    @Test
    public void getAllEvents() throws GeneralSecurityException, IOException, ParseException {
        String APIkey = "{\"installed\":{\"client_id\":\"676658923300-jefh7ko5cp9n7cf92vj427ltrd0rumo4.apps.googleusercontent.com\",\"project_id\":\"thought-vault-400423\",\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"token_uri\":\"https://oauth2.googleapis.com/token\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\",\"client_secret\":\"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\",\"redirect_uris\":[\"http://localhost\"]}}";
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
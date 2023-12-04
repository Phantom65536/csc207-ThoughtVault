
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
        GCalEventDataAccessInterface userDataAccessObject = new GCalDataAccessObject("{\"installed\":{\"client_id\":\"795633948902-lnv99a5r05977jor1cm9harbqhq4kcv2.apps.googleusercontent.com\",\"project_id\":\"thought-vault-400118\",\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"token_uri\":\"https://oauth2.googleapis.com/token\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\",\"client_secret\":\"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\",\"redirect_uris\":[\"http://localhost\"]}}");
        EntriesDataAccessInterface entriesDataAccessObject = new EventsDataAccessObject("./testEvent.json");
        String eventId = "v4k9a420nam9dhqtu9sq4mchr2_20231206T150000Z";

        // This creates a successPresenter that tests whether the test case is as we expect.
        GCalEventOutputBoundary successPresenter = new GCalEventOutputBoundary() {
            @Override
            public void prepareSuccessView(GCalEventOutputData evt) {
                Assert.assertEquals("Lab Meeting", evt.getTitle()); // check that event title matches
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
        interactor.importEvent(eventId);
    }

    @Test
    public void exportEvent() throws GeneralSecurityException, IOException, ParseException {
        String jsonCredentials = "{\"installed\":{\"client_id\":\"795633948902-lnv99a5r05977jor1cm9harbqhq4kcv2.apps.googleusercontent.com\",\"project_id\":\"thought-vault-400118\",\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"token_uri\":\"https://oauth2.googleapis.com/token\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\",\"client_secret\":\"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\",\"redirect_uris\":[\"http://localhost\"]}}";
        GCalEventDataAccessInterface userDataAccessObject = new GCalDataAccessObject(jsonCredentials);
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
        GCalEventDataAccessInterface userDataAccessObject = new GCalDataAccessObject("{\"installed\":{\"client_id\":\"795633948902-lnv99a5r05977jor1cm9harbqhq4kcv2.apps.googleusercontent.com\",\"project_id\":\"thought-vault-400118\",\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"token_uri\":\"https://oauth2.googleapis.com/token\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\",\"client_secret\":\"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\",\"redirect_uris\":[\"http://localhost\"]}}");
        EntriesDataAccessInterface entriesDataAccessObject = new EventsDataAccessObject("./testEvent.json");
        GCalEventOutputBoundary successPresenter = new GCalEventOutputBoundary() {
            @Override
            public void prepareSuccessView(GCalEventOutputData evt) {
                Assert.assertEquals("Lab Meeting", evt.getTitle()); // check that event title matches
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
        Assert.assertEquals("CS welcome to program event", listOfEvents.get(0).getTitle());
    }

    @Test
    public void switchToHome() {
    }
}
package view;

import com.google.api.client.auth.oauth2.Credential;
import data_access.EventsDataAccessObject;
import data_access.GCalDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.listView.ListViewModel;
import interface_adapter.importevents.ImportEventsController;
import interface_adapter.importevents.ImportEventsPresenter;
import interface_adapter.importevents.ImportEventsViewModel;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;
import use_case.gcalevent.GCalEventDataAccessInterface;
import use_case.gcalevent.GCalEventInteractor;
import use_case.EntriesDataAccessInterface;
import view.gcal.ImportEventsView;

import javax.swing.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Properties;

import static data_access.GCalDataAccessObject.getCredentials;
import static java.lang.Thread.sleep;

public class ImportEventsViewTest {

    @Test
    public void testGetSelectedEvent() throws IOException, GeneralSecurityException, ParseException {
        // Read the apiKey environment variable
        Properties prop = new Properties();
        InputStream input = null;
        input = new FileInputStream("gradle.properties");
        prop.load(input);
        String APIkey = prop.getProperty("apiKey");

        ImportEventsViewModel importEventsViewModel = new ImportEventsViewModel();
        ListViewModel homeViewModel = new ListViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        GCalEventDataAccessInterface userDataAccessObject = new GCalDataAccessObject();
        Credential credential = getCredentials(APIkey);
        userDataAccessObject.setUserCalendar(credential);
        EntriesDataAccessInterface entriesDataAccessObject = new EventsDataAccessObject("./testEvent.json");
        ImportEventsPresenter presenter = new ImportEventsPresenter(importEventsViewModel, homeViewModel, viewManagerModel);

        GCalEventInteractor inputBoundary = new GCalEventInteractor(userDataAccessObject, presenter, entriesDataAccessObject);
        ImportEventsController importEventsController = new ImportEventsController(inputBoundary);


        JPanel importEventsView = new ImportEventsView(importEventsViewModel, importEventsController);
        JFrame jf = new JFrame();
        jf.setContentPane(importEventsView);
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setSize(400,600);
        jf.show();

        // So that the app doesn't close automatically.
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Check that the selected event and the imported event are the same
        Assert.assertEquals(
                importEventsViewModel.getState().getSelectedEvent().getTitle(),
                importEventsViewModel.getState().getImportedEventSummary()
        );

        // Print out what the event titles are
        System.out.println("View model: " + importEventsViewModel.getState().getSelectedEvent().getTitle());
        System.out.println("Imported Event: " + importEventsViewModel.getState().getImportedEventSummary());
    }
}
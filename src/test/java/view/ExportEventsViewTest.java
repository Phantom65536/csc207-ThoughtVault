package view;

import com.google.api.client.auth.oauth2.Credential;
import data_access.EventsDataAccessObject;
import data_access.GCalDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.exportevents.ExportEventsController;
import interface_adapter.exportevents.ExportEventsPresenter;
import interface_adapter.exportevents.ExportEventsViewModel;
import interface_adapter.listView.ListViewModel;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;
import use_case.gcalevent.GCalEventDataAccessInterface;
import use_case.gcalevent.GCalEventInteractor;
import use_case.EntriesDataAccessInterface;
import view.gcal.ExportEventsView;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Properties;

import static data_access.GCalDataAccessObject.getCredentials;
import static java.lang.Thread.sleep;

public class ExportEventsViewTest {
    @Test
    public void testGetSelectedEvent() throws IOException, GeneralSecurityException, ParseException {
        // Read the apiKey environment variable
        Properties prop = new Properties();
        InputStream input = null;
        input = new FileInputStream("gradle.properties");
        prop.load(input);
        String APIkey = prop.getProperty("apiKey");

        ExportEventsViewModel exportEventsViewModel = new ExportEventsViewModel();
        ListViewModel homeViewModel = new ListViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        GCalEventDataAccessInterface userDataAccessObject = new GCalDataAccessObject();
        Credential credential = getCredentials(APIkey);
        userDataAccessObject.setUserCalendar(credential);
        EntriesDataAccessInterface entriesDataAccessObject = new EventsDataAccessObject("./testEvent.json");
        ExportEventsPresenter presenter = new ExportEventsPresenter(exportEventsViewModel, homeViewModel, viewManagerModel);

        GCalEventInteractor inputBoundary = new GCalEventInteractor(userDataAccessObject, presenter, entriesDataAccessObject);
        ExportEventsController exportEventsController = new ExportEventsController(inputBoundary);


        JPanel exportEventsView = new ExportEventsView(exportEventsViewModel, exportEventsController);
        JFrame jf = new JFrame();
        jf.setContentPane(exportEventsView);
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setSize(400,400);
        jf.show();

        // So that the app doesn't close automatically.
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Check that the selected event and the exported event are the same
        Assert.assertEquals(
                exportEventsViewModel.getState().getSelectedEvent().getTitle(),
                exportEventsViewModel.getState().getExportedEventSummary()
        );

        // Print out what the event titles are
        System.out.println("View model: " + exportEventsViewModel.getState().getSelectedEvent().getTitle());
        System.out.println("Exported Event: " + exportEventsViewModel.getState().getExportedEventSummary());

    }

    @Test
    public void testNoSelectedEvent() throws IOException, GeneralSecurityException, ParseException {
        // Read the apiKey environment variable
        Properties prop = new Properties();
        InputStream input = null;
        input = new FileInputStream("gradle.properties");
        prop.load(input);
        String APIkey = prop.getProperty("apiKey");

        ExportEventsViewModel exportEventsViewModel = new ExportEventsViewModel();
        HomeViewModel homeViewModel = new HomeViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        GCalEventDataAccessInterface userDataAccessObject = new GCalDataAccessObject();
        Credential credential = getCredentials(APIkey);
        userDataAccessObject.setUserCalendar(credential);
        EntriesDataAccessInterface entriesDataAccessObject = new EventsDataAccessObject("./testEvent.json");
        ExportEventsPresenter presenter = new ExportEventsPresenter(exportEventsViewModel, homeViewModel, viewManagerModel);

        GCalEventInteractor inputBoundary = new GCalEventInteractor(userDataAccessObject, presenter, entriesDataAccessObject);
        ExportEventsController exportEventsController = new ExportEventsController(inputBoundary);


        JPanel exportEventsView = new ExportEventsView(exportEventsViewModel, exportEventsController);
        JFrame jf = new JFrame();
        jf.setContentPane(exportEventsView);
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setSize(400,400);
        jf.show();

        // So that the app doesn't close automatically.
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Check that the selected event and the exported event are the same
        Assert.assertNull(
                exportEventsViewModel.getState().getSelectedEvent()
        );
        Assert.assertNull(
                exportEventsViewModel.getState().getExportedEventSummary()
        );

    }

    @Test
    public void testSwitchToHome() throws IOException, GeneralSecurityException, ParseException {
        // Read the apiKey environment variable
        Properties prop = new Properties();
        InputStream input = null;
        input = new FileInputStream("gradle.properties");
        prop.load(input);
        String APIkey = prop.getProperty("apiKey");

        ExportEventsViewModel exportEventsViewModel = new ExportEventsViewModel();
        HomeViewModel homeViewModel = new HomeViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        GCalEventDataAccessInterface userDataAccessObject = new GCalDataAccessObject();
        Credential credential = getCredentials(APIkey);
        userDataAccessObject.setUserCalendar(credential);
        EntriesDataAccessInterface entriesDataAccessObject = new EventsDataAccessObject("./testEvent.json");
        ExportEventsPresenter presenter = new ExportEventsPresenter(exportEventsViewModel, homeViewModel, viewManagerModel);

        GCalEventInteractor inputBoundary = new GCalEventInteractor(userDataAccessObject, presenter, entriesDataAccessObject);
        ExportEventsController exportEventsController = new ExportEventsController(inputBoundary);


        JPanel exportEventsView = new ExportEventsView(exportEventsViewModel, exportEventsController);
        JFrame jf = new JFrame();
        jf.setContentPane(exportEventsView);
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setSize(400,400);
        jf.show();

        // So that the app doesn't close automatically.
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Check that the selected event and the exported event are the same
        Assert.assertEquals(
                viewManagerModel.getActiveView(),
                homeViewModel.getViewName()
        );
    }


}
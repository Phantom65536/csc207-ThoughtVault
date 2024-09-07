package app;

import data_access.*;
import com.google.api.client.auth.oauth2.Credential;
import interface_adapter.ViewManagerModel;
import interface_adapter.exportevents.ExportEventsViewModel;
import interface_adapter.importevents.ImportEventsViewModel;
import interface_adapter.listView.ListViewModel;
import interface_adapter.localEvent.LocalEventController;
import interface_adapter.localEvent.LocalEventPresenter;
import interface_adapter.localEvent.LocalEventViewModel;
import interface_adapter.log_in_out.LogInViewModel;
import interface_adapter.note.NoteController;
import interface_adapter.note.NotePresenter;
import interface_adapter.note.NoteViewModel;
import interface_adapter.sign_up.SignUpViewModel;
import org.json.simple.parser.ParseException;

import use_case.EntriesDataAccessInterface;
import use_case.gcalevent.GCalEventDataAccessInterface;
import use_case.localEvent.LocalEventInteractor;
import use_case.note.NoteInteractor;
import use_case.user.UserDataAccessInterface;
import view.ViewManager;
import view.gcal.ExportEventsView;
import view.gcal.ImportEventsView;
import view.localEvent.CreateLocalEventView;
import view.localEvent.DetailedLocalEventView;
import view.localEvent.EditLocalEventView;
import view.note.CreateNoteView;
import view.user.LogInView;
import view.user.SignUpView;
import view.ListView;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Properties;

import static data_access.GCalDataAccessObject.getCredentials;

public class Main {
    public static void main(String[] args) throws IOException, GeneralSecurityException, ParseException {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Thought Vault");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // Read the apiKey environment variable
        Properties prop = new Properties();
        InputStream input = null;
        input = new FileInputStream("gradle.properties");
        prop.load(input);
        String APIkey = prop.getProperty("apiKey");

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        LogInViewModel loginViewModel = new LogInViewModel();
        SignUpViewModel signupViewModel = new SignUpViewModel();
        ExportEventsViewModel exportEventsViewModel = new ExportEventsViewModel();
        ImportEventsViewModel importEventsViewModel = new ImportEventsViewModel();
        ListViewModel listViewModel = new ListViewModel();

        GCalEventDataAccessInterface gCalEventDataAccessInterface = new GCalDataAccessObject();
        Credential credential = getCredentials(APIkey);
        gCalEventDataAccessInterface.setUserCalendar(credential);
        EventsDataAccessObject eventsDataAccessObject = new EventsDataAccessObject("./testEvents.json");
        UserDataAccessInterface userDataAccessObject = new UserDataAccessObject("./users.json");
        NotesDataAccessObject notesDataAccessObject = new NotesDataAccessObject("./testNotes.json");

        NoteViewModel noteViewModel = new NoteViewModel("detailed note view");
        NoteViewModel noteCreationViewModel = new NoteViewModel("create note view");
        NoteViewModel noteEditViewModel = new NoteViewModel("edit note view");
        NotePresenter notePresenter = new NotePresenter(
                viewManagerModel, noteViewModel, noteCreationViewModel, noteEditViewModel, listViewModel
        );
        NoteInteractor noteInteractor = new NoteInteractor(notePresenter, notesDataAccessObject);
        NoteController noteController = new NoteController(noteInteractor);

        LocalEventViewModel LocalEventViewModel = new LocalEventViewModel("edit event view");
        LocalEventViewModel detailedLocalEventViewModel = new LocalEventViewModel("detailed local event view");
        LocalEventViewModel localEventCreationViewModel = new LocalEventViewModel("create event view");
        interface_adapter.localEvent.LocalEventPresenter LocalEventPresenter = new LocalEventPresenter(viewManagerModel,
                detailedLocalEventViewModel,
                localEventCreationViewModel,
                LocalEventViewModel,
                listViewModel);
        LocalEventInteractor inputBoundary = new LocalEventInteractor(eventsDataAccessObject, LocalEventPresenter);
        LocalEventController localEventController = new LocalEventController(inputBoundary);

        ImportEventsView importEventsView = ImportEventsUseCaseFactory.create(
                viewManagerModel, importEventsViewModel, listViewModel, 
                gCalEventDataAccessInterface, eventsDataAccessObject);
        views.add(importEventsView, importEventsView.viewName);

        ExportEventsView exportEventsView = ExportEventsUseCaseFactory.create(
                viewManagerModel, exportEventsViewModel, listViewModel, 
                gCalEventDataAccessInterface, eventsDataAccessObject);
        views.add(exportEventsView, exportEventsView.viewName);

        SignUpView signUpView = (SignUpView) UsersUseCaseFactory.create(
                viewManagerModel, loginViewModel, signupViewModel, listViewModel, userDataAccessObject,
                gCalEventDataAccessInterface, noteController, localEventController
        )[0];
        views.add(signUpView, signupViewModel.getViewName());

        LogInView logInView = (LogInView) UsersUseCaseFactory.create(
                viewManagerModel, loginViewModel, signupViewModel, listViewModel, userDataAccessObject,
                gCalEventDataAccessInterface, noteController, localEventController
        )[1];
        views.add(logInView, loginViewModel.getViewName());

        ListView listView = LocalEventsUseCaseFactory.createListView(viewManagerModel, detailedLocalEventViewModel, localEventCreationViewModel, localEventCreationViewModel, listViewModel, importEventsViewModel, exportEventsViewModel, noteViewModel, noteController);
        views.add(listView, listViewModel.getViewName());

        CreateLocalEventView createLocalEventView = LocalEventsUseCaseFactory.createLocalEventView(
                viewManagerModel, detailedLocalEventViewModel, localEventCreationViewModel, localEventCreationViewModel, listViewModel);
        views.add(createLocalEventView, localEventCreationViewModel.getViewName());

        EditLocalEventView editLocalEventView = LocalEventsUseCaseFactory.createEditLocalEventView(viewManagerModel, detailedLocalEventViewModel, localEventCreationViewModel, localEventCreationViewModel, listViewModel);
        views.add(editLocalEventView, LocalEventViewModel.getViewName());

        DetailedLocalEventView detailedLocalEventView = LocalEventsUseCaseFactory.createDetailedLocalEventView(viewManagerModel, detailedLocalEventViewModel, localEventCreationViewModel, localEventCreationViewModel, listViewModel);
        views.add(detailedLocalEventView, detailedLocalEventViewModel.getViewName());

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}

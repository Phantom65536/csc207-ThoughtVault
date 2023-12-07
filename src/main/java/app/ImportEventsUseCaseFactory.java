package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.listView.ListViewModel;
import interface_adapter.importevents.ImportEventsController;
import interface_adapter.importevents.ImportEventsPresenter;
import interface_adapter.importevents.ImportEventsViewModel;
import use_case.gcalevent.GCalEventDataAccessInterface;
import use_case.gcalevent.GCalEventInputBoundary;
import use_case.gcalevent.GCalEventInteractor;
import use_case.gcalevent.GCalEventOutputBoundary;
import use_case.EntriesDataAccessInterface;
import view.gcal.ImportEventsView;

import javax.swing.*;
import java.io.IOException;

public class ImportEventsUseCaseFactory {

    /** Prevent instantiation. */
    private ImportEventsUseCaseFactory(){}

    public static ImportEventsView create(
            ViewManagerModel viewManagerModel,
            ImportEventsViewModel importEventsViewModel,
            ListViewModel listViewModel,
            GCalEventDataAccessInterface gcalDataAccessObject,
            EntriesDataAccessInterface entriesDataAccessObject) {

        try {
            ImportEventsController importEventsController = createImportEventsUseCase(viewManagerModel,
                    importEventsViewModel, listViewModel, gcalDataAccessObject, entriesDataAccessObject);
            return new ImportEventsView(importEventsViewModel, importEventsController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not instantiate Import Events View.");
        }

        return null;
    }

    private static ImportEventsController createImportEventsUseCase(
            ViewManagerModel viewManagerModel,
            ImportEventsViewModel importEventsViewModel,
            ListViewModel listViewModel,
            GCalEventDataAccessInterface gcalDataAccessObject,
            EntriesDataAccessInterface entriesDataAccessObject) throws IOException {

        // Pass GCalEventOutputBoundary to the Presenter.
        GCalEventOutputBoundary gCalEventOutputBoundary = new ImportEventsPresenter(importEventsViewModel, listViewModel, viewManagerModel);

        GCalEventInputBoundary importEventsInteractor = new GCalEventInteractor(
                gcalDataAccessObject, gCalEventOutputBoundary, entriesDataAccessObject);

        return new ImportEventsController(importEventsInteractor);
    }
}

package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.exportevents.ExportEventsController;
import interface_adapter.exportevents.ExportEventsPresenter;
import interface_adapter.exportevents.ExportEventsViewModel;
import interface_adapter.home.HomeViewModel;
import use_case.gcalevent.GCalEventDataAccessInterface;
import use_case.gcalevent.GCalEventInputBoundary;
import use_case.gcalevent.GCalEventInteractor;
import use_case.gcalevent.GCalEventOutputBoundary;
import use_case.local_event.EntriesDataAccessInterface;
import view.ExportEventsView;
import view.ImportEventsView;

import javax.swing.*;
import java.io.IOException;

public class ExportEventsUseCaseFactory {

    /** Prevent instantiation. */
    private ExportEventsUseCaseFactory(){}

    public static ExportEventsView create(
            ViewManagerModel viewManagerModel,
            ExportEventsViewModel exportEventsViewModel,
            HomeViewModel homeViewModel,
            GCalEventDataAccessInterface gcalDataAccessObject,
            EntriesDataAccessInterface entriesDataAccessObject) {

        try {
            ExportEventsController exportEventsController = createExportEventsUseCase(viewManagerModel,
                    exportEventsViewModel, homeViewModel, gcalDataAccessObject, entriesDataAccessObject);
            return new ExportEventsView(exportEventsViewModel, exportEventsController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not instantiate Export Events View.");
        }

        return null;
    }

    private static ExportEventsController createExportEventsUseCase(
            ViewManagerModel viewManagerModel,
            ExportEventsViewModel exportEventsViewModel,
            HomeViewModel homeViewModel,
            GCalEventDataAccessInterface gcalDataAccessObject,
            EntriesDataAccessInterface entriesDataAccessObject) throws IOException {

        // Pass GCalEventOutputBoundary to the Presenter.
        GCalEventOutputBoundary gCalEventOutputBoundary = new ExportEventsPresenter(exportEventsViewModel, homeViewModel, viewManagerModel);

        GCalEventInputBoundary exportEventsInteractor = new GCalEventInteractor(
                gcalDataAccessObject, gCalEventOutputBoundary, entriesDataAccessObject);

        return new ExportEventsController(exportEventsInteractor);
    }
}

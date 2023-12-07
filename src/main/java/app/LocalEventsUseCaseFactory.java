package app;

import data_access.EventsDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.listView.ListViewModel;
import interface_adapter.localEvent.LocalEventController;
import interface_adapter.localEvent.LocalEventPresenter;
import interface_adapter.localEvent.LocalEventViewModel;
import org.json.simple.parser.ParseException;
import use_case.localEvent.LocalEventInputBoundary;
import use_case.localEvent.LocalEventInteractor;
import use_case.localEvent.LocalEventOutputBoundary;
import view.localEvent.CreateLocalEventView;
import view.localEvent.DetailedLocalEventView;
import view.localEvent.EditLocalEventView;

import javax.swing.*;
import java.io.IOException;

public class LocalEventsUseCaseFactory {

    /** Prevent instantiation. */
    private LocalEventsUseCaseFactory() {}

    public static CreateLocalEventView createLocalEventView(
            ViewManagerModel viewManagerModel,
            LocalEventViewModel detailedLocalEventViewModel,
            LocalEventViewModel localEventCreationViewModel,
            LocalEventViewModel localEventEditViewModel,
            ListViewModel listViewModel) {

        try {
            LocalEventController localEventController = createLocalEventUseCase(
                    viewManagerModel, detailedLocalEventViewModel, localEventCreationViewModel,
                    localEventEditViewModel, listViewModel
            );
            return new CreateLocalEventView(localEventCreationViewModel, viewManagerModel, localEventController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static EditLocalEventView createEditLocalEventView(
            ViewManagerModel viewManagerModel,
            LocalEventViewModel detailedLocalEventViewModel,
            LocalEventViewModel localEventCreationViewModel,
            LocalEventViewModel localEventEditViewModel,
            ListViewModel listViewModel) {

        try {
            LocalEventController localEventController = createLocalEventUseCase(
                    viewManagerModel, detailedLocalEventViewModel, localEventCreationViewModel,
                    localEventEditViewModel, listViewModel
            );
            return new EditLocalEventView(localEventEditViewModel, viewManagerModel, localEventController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static DetailedLocalEventView createDetailedLocalEventView(
            ViewManagerModel viewManagerModel,
            LocalEventViewModel detailedLocalEventViewModel,
            LocalEventViewModel localEventCreationViewModel,
            LocalEventViewModel localEventEditViewModel,
            ListViewModel listViewModel) {

        try {
            LocalEventController localEventController = createLocalEventUseCase(
                    viewManagerModel, detailedLocalEventViewModel, localEventCreationViewModel,
                    localEventEditViewModel, listViewModel
            );
            return new DetailedLocalEventView(detailedLocalEventViewModel, localEventController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private static LocalEventController createLocalEventUseCase(
            ViewManagerModel viewManagerModel, LocalEventViewModel detailedLocalEventViewModel,
            LocalEventViewModel localEventCreationViewModel,
            LocalEventViewModel localEventEditViewModel,
            ListViewModel listViewModel
            ) throws IOException, ParseException {

        // Notice how we pass this method's parameters to the Presenter.
        LocalEventOutputBoundary localEventOutputBoundary = new LocalEventPresenter(
                viewManagerModel, detailedLocalEventViewModel, localEventCreationViewModel, localEventEditViewModel, listViewModel
        );

        EventsDataAccessObject eventsDataAccessObject = new EventsDataAccessObject("./testEvents.json");

        LocalEventInputBoundary localEventInteractor = new LocalEventInteractor(
                eventsDataAccessObject, localEventOutputBoundary
                );

        return new LocalEventController(localEventInteractor);
    }
}

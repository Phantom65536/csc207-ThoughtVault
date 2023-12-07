package interface_adapter.exportevents;

import use_case.gcalevent.GCalEventInputBoundary;
import use_case.gcalevent.GCalEventInputData;
import use_case.localEvent.LocalEventOutputData;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller class for exporting events.
 */
public class ExportEventsController {
    final GCalEventInputBoundary gCalEventUseCaseInteractor;

    /**
     * Set the gCalEventUseCaseInteractor to be used for this controller
     * @param gCalEventUseCaseInteractor
     */
    public ExportEventsController(GCalEventInputBoundary gCalEventUseCaseInteractor){
        this.gCalEventUseCaseInteractor = gCalEventUseCaseInteractor;
    }

    /**
     * Export a LocalEvent to the user's google calendar based off a localEventId.
     * @param localEventId
     * */
    public void execute(int localEventId) throws IOException {
        gCalEventUseCaseInteractor.exportEvent(localEventId);
    }

    /**
     * Switch to Home view
     * */
    public void switchToHome(){
        gCalEventUseCaseInteractor.switchToHome();
    }

    /**
     * Gets all events to display to the user.
     * */
    public ArrayList<GCalEventInputData> getAllEvents() throws IOException {
        return gCalEventUseCaseInteractor.getAllEvents();
    }

    /**
     * Gets all Local Events to display to the user
     * */
    public ArrayList<LocalEventOutputData> getAllLocalEvents() throws IOException {
        return gCalEventUseCaseInteractor.getAllLocalEvents();
    }
}
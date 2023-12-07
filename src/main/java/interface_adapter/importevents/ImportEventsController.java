package interface_adapter.importevents;

import use_case.gcalevent.GCalEventInputBoundary;
import use_case.gcalevent.GCalEventInputData;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller class for importing events.
 */
public class ImportEventsController {
    final GCalEventInputBoundary gCalEventUseCaseInteractor;

    /**
     * Set the gCalEventUseCaseInteractor to be used for this controller
     * @param gCalEventUseCaseInteractor
     */
    public ImportEventsController(GCalEventInputBoundary gCalEventUseCaseInteractor){
        this.gCalEventUseCaseInteractor = gCalEventUseCaseInteractor;
    }

    /**
     * Imports an Event from the user's google calendar based off a eventId.
     * @param eventId
     * */
    public void execute(String eventId) throws IOException {
        gCalEventUseCaseInteractor.importEvent(eventId);
    }

    /**
     * Gets all events to display to the user.
     * */
    public ArrayList<GCalEventInputData> getAllEvents() throws IOException {
        return gCalEventUseCaseInteractor.getAllEvents();
    }

    /**
     * Switch to Home view
     * */
    public void switchToHome(){
        gCalEventUseCaseInteractor.switchToHome();
    }
}

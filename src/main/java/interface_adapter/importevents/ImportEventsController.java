package interface_adapter.importevents;

import use_case.gcalevent.GCalEventInputBoundary;

import java.io.IOException;

public class ImportEventsController {
    final GCalEventInputBoundary gCalEventUseCaseInteractor;
    public ImportEventsController(GCalEventInputBoundary gCalEventUseCaseInteractor){
        this.gCalEventUseCaseInteractor = gCalEventUseCaseInteractor;
    }
    public void execute(String eventId) throws IOException {
        gCalEventUseCaseInteractor.importEvent(eventId);
    }
}

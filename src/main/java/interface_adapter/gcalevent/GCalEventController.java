package interface_adapter.gcalevent;

import use_case.external_event.ExternalEventInputBoundary;

import java.io.IOException;

public class GCalEventController {
    final ExternalEventInputBoundary externalEventUseCaseInteractor;
    public GCalEventController(ExternalEventInputBoundary externalEventUseCaseInteractor) {
        this.externalEventUseCaseInteractor = externalEventUseCaseInteractor;
    }

    public void importEvent(String eventId) throws IOException {
        externalEventUseCaseInteractor.importEvent(eventId);
    }

    public void exportEvent(String eventId) throws IOException {
        externalEventUseCaseInteractor.exportEvent(eventId);
    }
}

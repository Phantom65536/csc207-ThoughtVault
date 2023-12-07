package interface_adapter.exportevents;

import output_data.LocalEventOutputData;
import use_case.gcalevent.GCalEventInputBoundary;
import use_case.gcalevent.GCalEventInputData;

import java.io.IOException;
import java.util.ArrayList;

public class ExportEventsController {
    final GCalEventInputBoundary gCalEventUseCaseInteractor;
    public ExportEventsController(GCalEventInputBoundary gCalEventUseCaseInteractor){
        this.gCalEventUseCaseInteractor = gCalEventUseCaseInteractor;
    }
    public void execute(int localEventId) throws IOException {
        gCalEventUseCaseInteractor.exportEvent(localEventId);
    }

    public void switchToHome(){
        gCalEventUseCaseInteractor.switchToHome();
    }

    public ArrayList<GCalEventInputData> getAllEvents() throws IOException {
        return gCalEventUseCaseInteractor.getAllEvents();
    }

    public ArrayList<LocalEventOutputData> getAllLocalEvents() throws IOException {
        return gCalEventUseCaseInteractor.getAllLocalEvents();
    }
}
package interface_adapter.importevents;

import use_case.gcalevent.GCalEventInputData;

import java.util.ArrayList;

public class ImportEventsState {
    private int selectedEventIndex;
    private GCalEventInputData selectedEvent;
    private ArrayList<GCalEventInputData> listOfEvents;
    private String importedEventSummary = null;
    private String importEventError = null;

    public ImportEventsState(ImportEventsState copy) {
        selectedEventIndex = copy.selectedEventIndex;
        selectedEvent = copy.selectedEvent;
        listOfEvents = copy.listOfEvents;
        importedEventSummary = copy.importedEventSummary;
    }

    public ImportEventsState() {
    }

    public int getSelectedEventIndex() {
        return selectedEventIndex;
    }

    public GCalEventInputData getSelectedEvent() {
        return selectedEvent;
    }

    public ArrayList<GCalEventInputData> getListOfEvents() {
        return listOfEvents;
    }

    public String getSelectedEventId() {
        return selectedEvent.getEventId();
    }

    public String getImportedEventSummary(){
        return importedEventSummary;
    }

    public String getImportEventError() {
        return importEventError;
    }

    public void setSelectedEventIndex(int selectedEventIndex) {
        this.selectedEventIndex = selectedEventIndex;
    }

    public void setSelectedEvent(int selectedEventIndex) {
        this.selectedEvent = listOfEvents.get(selectedEventIndex);
    }

    public void setImportedEventSummary(String importedEventSummary) {
        this.importedEventSummary = importedEventSummary;
    }

    public void setImportEventError(String importEventError) {
        this.importEventError = importEventError;
    }

    public void setListOfEvents(ArrayList<GCalEventInputData> listOfEvents) {
        this.listOfEvents = listOfEvents;
    }

}

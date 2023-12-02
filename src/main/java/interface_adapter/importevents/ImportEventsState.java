package interface_adapter.importevents;

import com.google.api.services.calendar.model.Event;

import java.util.List;

public class ImportEventsState {
    private int selectedEventIndex;
    private Event selectedEvent;
    private List<Event> listOfEvents;
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

    public Event getSelectedEvent() {
        return selectedEvent;
    }

    public List<Event> getListOfEvents() {
        return listOfEvents;
    }

    public String getSelectedEventId() {
        return selectedEvent.getId();
    }

    public String getImportedEventSummary(){
        return importedEventSummary;
    }

    public String getImportEventError() {
        return importEventError;
    }

    //    public String getSelectedEventTitle() {
//        return selectedEvent.getSummary();
//    }
//
//    public String getImportedEventSummary() {
//        return importedEventSummary;
//    }

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
}

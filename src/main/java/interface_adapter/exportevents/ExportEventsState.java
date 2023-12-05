package interface_adapter.exportevents;

import com.google.api.services.calendar.model.Event;

import java.util.List;

public class ExportEventsState {
    private int entryID;
    private int selectedEventIndex;
    private Event selectedEvent;
    private List<Event> listOfEvents;
    private String exportedEventSummary  = null;
    private String exportEventError = null;
    public ExportEventsState(ExportEventsState copy){
        entryID = copy.entryID;
        selectedEventIndex = copy.selectedEventIndex;
        selectedEvent = copy.selectedEvent;
        listOfEvents = copy.listOfEvents;
        exportedEventSummary = copy.exportedEventSummary;
    }
    public ExportEventsState(){}
    public int getEntryID(){return entryID;}
    public Event getSelectedEvent(){
        return selectedEvent;
    }

    public List<Event> getListOfEvents() {
        return listOfEvents;
    }

    public String getSelectedEventId(){
        return selectedEvent.getId();
    }
    public String getSelectedEventTitle(){
        return selectedEvent.getSummary();
    }
    public int getSelectedEventIndex(){
        return selectedEventIndex;
    }

    public String getExportEventError() {
        return exportEventError;
    }

    public String getExportedEventSummary() {
        return exportedEventSummary;
    }

    public void setSelectedEventIndex(int selectedEventIndex){
        this.selectedEventIndex = selectedEventIndex;
    }
    public void setSelectedEvent(int selectedEventIndex){
        this.selectedEvent = listOfEvents.get(selectedEventIndex);
    }
    public void setExportedEventSummary(String exportedEventSummary){
        this.exportedEventSummary = exportedEventSummary;
    }
    public void setExportEventError(String error){
        this.exportEventError = error;
    }
}

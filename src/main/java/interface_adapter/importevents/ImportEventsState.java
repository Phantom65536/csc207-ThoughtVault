package interface_adapter.importevents;

import use_case.gcalevent.GCalEventInputData;

import java.util.ArrayList;

/**
 * State for ImportEventsViewModel
 */
public class ImportEventsState {
    private int selectedEventIndex;
    private GCalEventInputData selectedEvent;
    private ArrayList<GCalEventInputData> listOfEvents;
    private String importedEventSummary = null;
    private String importEventError = null;

    /**
     * Instantiate ImportEventsState
     */
    public ImportEventsState() {
    }
    /**
     * Get the selected event index of this state
     */
    public int getSelectedEventIndex() {
        return selectedEventIndex;
    }

    /**
     * Get the selected event by the user
     * */
    public GCalEventInputData getSelectedEvent() {
        return selectedEvent;
    }

    /**
     * Get a list of all Google Calendar events by the user to display to user
     * */
    public ArrayList<GCalEventInputData> getListOfEvents() {
        return listOfEvents;
    }

    /**
     * From the list of local events displayed to the user,
     * get the selected event (selected by the user) ID.*/
    public String getSelectedEventId() {
        return selectedEvent.getEventId();
    }

    /**
     * If importing event is successful, return the title of the imported event
     * */
    public String getImportedEventSummary(){
        return importedEventSummary;
    }

    /**
     * Return an error if importing event is unsuccessful
     * */
    public String getImportEventError() {
        return importEventError;
    }

    /**
     * Set the selected event index in the state
     * */
    public void setSelectedEventIndex(int selectedEventIndex) {
        this.selectedEventIndex = selectedEventIndex;
    }
    /**
     * Set the selected event in the state
     * */
    public void setSelectedEvent(int selectedEventIndex) {
        this.selectedEvent = listOfEvents.get(selectedEventIndex);
    }
    /**
     * Set the imported event title in the state if successful
     * */
    public void setImportedEventSummary(String importedEventSummary) {
        this.importedEventSummary = importedEventSummary;
    }
    /**
     * Set an error in state if importing event is unsuccessful
     * */
    public void setImportEventError(String importEventError) {
        this.importEventError = importEventError;
    }

    /**
     * Set a list of events in state to display to user.
     * */
    public void setListOfEvents(ArrayList<GCalEventInputData> listOfEvents) {
        this.listOfEvents = listOfEvents;
    }

}

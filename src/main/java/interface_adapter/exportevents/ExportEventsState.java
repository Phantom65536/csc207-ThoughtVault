package interface_adapter.exportevents;

import use_case.gcalevent.GCalEventInputData;
import use_case.localEvent.LocalEventOutputData;

import java.util.ArrayList;

/**
 * State for ExportEventsViewModel
 */
public class ExportEventsState {
    private int entryID;
    private int selectedEventIndex;
    private LocalEventOutputData selectedEvent;
    private ArrayList<GCalEventInputData> listOfEvents;
    private ArrayList<LocalEventOutputData> listOfLocalEvents;
    private String exportedEventSummary  = null;
    private String exportEventError = null;

    /**
     * Instantiate ExportEventsState
     */
    public ExportEventsState(){}

    /**
     * Get the entryID of this state
     */
    public int getEntryID(){return entryID;}

    /**
     * Get the selected event by the user
     * */
    public LocalEventOutputData getSelectedEvent(){
        return selectedEvent;
    }

    /**
     * Get a list of all local events by the user
     * */
    public ArrayList<LocalEventOutputData> getListOfLocalEvents(){return listOfLocalEvents;}

    /**
     * From the list of local events displayed to the user,
     * get the selected event (selected by the user) ID.*/
    public int getSelectedEventId(){
        return selectedEvent.getID();
    }

    /**
     * From the list of local events displayed to the user,
     * get the selected event index.
     * */
    public int getSelectedEventIndex(){
        return selectedEventIndex;
    }

    /**
     * Return an error if exporting event is unsuccessful
     * */
    public String getExportEventError() {
        return exportEventError;
    }

    /**
     * If exporting event is successful, return the title of the exported event
     * */
    public String getExportedEventSummary() {
        return exportedEventSummary;
    }

    /**
     * Set the selected event index in the state
     * */
    public void setSelectedEventIndex(int selectedEventIndex){
        this.selectedEventIndex = selectedEventIndex;
    }
    /**
     * Set the selected event in the state
     * */
    public void setSelectedEvent(int selectedEventIndex){
        this.selectedEvent = listOfLocalEvents.get(selectedEventIndex);
    }
    /**
     * Set the exported event title in the state
     * */
    public void setExportedEventSummary(String exportedEventSummary){
        this.exportedEventSummary = exportedEventSummary;
    }
    /**
     * Set the error in the state if exporting event is unsuccessful
     * */
    public void setExportEventError(String error){
        this.exportEventError = error;
    }

    /**
     * Set the list of events to be displayed to user
     * @param listOfEvents
     * */
    public void setListOfEvents(ArrayList<GCalEventInputData> listOfEvents) {
        this.listOfEvents = listOfEvents;
    }

    /**
     * Set the list of the local events to be displayed to the user
     * @param listOfLocalEvents
     * */
    public void setListOfLocalEvents(ArrayList<LocalEventOutputData> listOfLocalEvents){
        this.listOfLocalEvents = listOfLocalEvents;
    }
}

package interface_adapter.exportevents;

import use_case.gcalevent.GCalEventInputData;
import use_case.localEvent.LocalEventOutputData;

import java.util.ArrayList;

public class ExportEventsState {
    private int entryID;
    private int selectedEventIndex;
    private LocalEventOutputData selectedEvent;
    private ArrayList<GCalEventInputData> listOfEvents;
    private ArrayList<LocalEventOutputData> listOfLocalEvents;
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
    public LocalEventOutputData getSelectedEvent(){
        return selectedEvent;
    }

    public ArrayList<GCalEventInputData> getListOfEvents() {
        return listOfEvents;
    }
    public ArrayList<LocalEventOutputData> getListOfLocalEvents(){return listOfLocalEvents;}

    public int getSelectedEventId(){
        return selectedEvent.getID();
    }
    public String getSelectedEventTitle(){
        return selectedEvent.getTitle();
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
        this.selectedEvent = listOfLocalEvents.get(selectedEventIndex);
    }
    public void setExportedEventSummary(String exportedEventSummary){
        this.exportedEventSummary = exportedEventSummary;
    }
    public void setExportEventError(String error){
        this.exportEventError = error;
    }
    public void setListOfEvents(ArrayList<GCalEventInputData> listOfEvents) {
        this.listOfEvents = listOfEvents;
    }
    public void setListOfLocalEvents(ArrayList<LocalEventOutputData> listOfLocalEvents){
        this.listOfLocalEvents = listOfLocalEvents;
    }
}

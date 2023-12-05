package interface_adapter.localEvent;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

import java.beans.PropertyChangeSupport;

public class LocalEventViewModel extends ViewModel {
    public static final String TITLE_VIEW = "Event view";

    public static final String TITLE_LABEL = "Title: ";

    public static final String DATE_LABEL = "Date: ";

    public static final String START_TIME_LABEL = "Start time: ";

    public static final String END_TIME_LABEL = "End time: ";

    public static final String LOCATION_LABEL = "Location: ";

    public static final String DESCRIPTION_LABEL = "Description: ";

    public static final String IS_WORK_LABEL = "Is Work: ";

    public static final String PINNED_LABEL = "Pinned: ";

    public static final String SUB_EVENTS_LABEL = "Sub-events: ";

    private LocalEventState state = new LocalEventState();

    public LocalEventViewModel(String viewName) {
        super(viewName);
    }

    public void setState(LocalEventState state) {
        this.state = state;
    }

    public LocalEventState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(
            this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null,
                this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}

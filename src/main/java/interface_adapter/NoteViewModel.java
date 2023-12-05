package interface_adapter;

import java.beans.PropertyChangeListener;

import java.beans.PropertyChangeSupport;

public class NoteViewModel extends ViewModel {
    public static final String TITLE_VIEW = "Note view";

    public static final String TITLE_LABEL = "Title: ";

    public static final String LOCATION_LABEL = "Location: ";

    public static final String DESCRIPTION_LABEL = "Description: ";

    public static final String IS_WORK_LABEL = "Is Work: ";

    public static final String PINNED_LABEL = "Pinned: ";

    public static final String SUB_EVENTS_LABEL = "Sub-events: ";

    private NoteState state = new NoteState();

    public NoteViewModel(String viewName) {
        super(viewName);
    }

    public void setState(NoteState state) {
        this.state = state;
    }

    public NoteState getState() {
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

package interface_adapter.exportevents;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The view model for exporting events
 */
public class ExportEventsViewModel extends ViewModel {
    public final String TITLE_LABEL = "Export Events View";
    public static final String EXPORT_EVENTS_BUTTON_LABEL = "Export Events";
    private ExportEventsState state = new ExportEventsState();

    /**
     * Set the view name (identifier) of this view model for switching view
     */
    public ExportEventsViewModel() {
        super("export events");
    }

    /**
     * Set the state to a new state
     * @param state
     */
    public void setState(ExportEventsState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Create an event notification to inform the corresponding ExportEventsView of any change in state
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Set listener to be the listener of any event notified by this view model
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Get the current state of this view model
     * @return state
     */
    public ExportEventsState getState() {
        return state;
    }
}

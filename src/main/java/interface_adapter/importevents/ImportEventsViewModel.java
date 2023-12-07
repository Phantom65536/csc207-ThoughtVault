package interface_adapter.importevents;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The view model for importing events
 */
public class ImportEventsViewModel extends ViewModel {
    public final String TITLE_LABEL = "Import Events View";
    public static final String IMPORT_EVENTS_BUTTON_LABEL = "Import Events";
    private ImportEventsState state = new ImportEventsState();

    /**
     * Set the view name (identifier) of this view model for switching view
     */
    public ImportEventsViewModel() {
        super("import events");
    }

    /**
     * Set the state to a new state
     * @param state
     */
    public void setState(ImportEventsState state) {
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
    public ImportEventsState getState() {
        return state;
    }
}

package interface_adapter.importevents;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ImportEventsViewModel extends ViewModel {
    public final String TITLE_LABEL = "Import Events View";
    public static final String IMPORT_EVENTS_BUTTON_LABEL = "Import Events";
    private ImportEventsState state = new ImportEventsState();

    public ImportEventsViewModel() {
        super("import events");
    }

    public void setState(ImportEventsState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ImportEventsState getState() {
        return state;
    }
}

package interface_adapter.exportevents;

import interface_adapter.ViewModel;
import interface_adapter.exportevents.ExportEventsState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ExportEventsViewModel extends ViewModel {
    public final String TITLE_LABEL = "Export Events View";
    public static final String EXPORT_EVENTS_BUTTON_LABEL = "Export Events";
    private ExportEventsState state = new ExportEventsState();

    public ExportEventsViewModel() {
        super("export events");
    }

    public void setState(ExportEventsState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ExportEventsState getState() {
        return state;
    }
}

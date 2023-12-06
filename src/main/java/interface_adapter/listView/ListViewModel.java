package interface_adapter.listView;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

import java.beans.PropertyChangeSupport;

public class ListViewModel extends ViewModel {
    public static final String HOME_BUTTON_LABEL = "Home";
    public static final String IMPORT_EVENTS_BUTTON_LABEL = "Import Events";
    public static final String EXPORT_EVENTS_BUTTON_LABEL = "Export Events";
    private ListViewState state;

    public ListViewModel() {
        super("List view");
    }

    public void setState(ListViewState state) {
        this.state = state;
    }

    public ListViewState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(
            this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}

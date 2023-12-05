package interface_adapter.home;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HomeViewModel extends ViewModel {
    public static final String TITLE_VIEW = "Home view";
    public static final String HOME_BUTTON_LABEL = "Home";
    public static final String IMPORT_EVENTS_BUTTON_LABEL = "Import Events";
    public static final String EXPORT_EVENTS_BUTTON_LABEL = "Export Events";
    private HomeState state = new HomeState();
    public HomeViewModel() { super("Home view"); }
    public void setState(HomeState state) {
        this.state = state;
    }

    public HomeState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}

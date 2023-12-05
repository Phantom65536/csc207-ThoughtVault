package interface_adapter;

import java.beans.PropertyChangeListener;

import java.beans.PropertyChangeSupport;

public class ListViewModel extends ViewModel {
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

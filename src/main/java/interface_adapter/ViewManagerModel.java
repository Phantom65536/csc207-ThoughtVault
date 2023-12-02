package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewManagerModel {

    private String activeViewName;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public String getActiveView() {
        return activeViewName;
    }

    public void setActiveView(String activeView) {
        this.activeViewName = activeView;
    }

<<<<<<< HEAD
    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
=======
>>>>>>> 8a370b815b9e81e3465ed189b97b54d70909ee1b
    public void firePropertyChanged() {
        support.firePropertyChange("view", null, this.activeViewName);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
<<<<<<< HEAD

=======
>>>>>>> 8a370b815b9e81e3465ed189b97b54d70909ee1b

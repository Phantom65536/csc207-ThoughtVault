package interface_adapter.log_in_out;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LogInViewModel extends ViewModel {
    public static final String TITLE_VIEW = "Log in view";
    public static final String USERNAME_LABEL = "Username: ";
    public static final String PASSWORD_LABEL = "Password: ";
    public static final String LOGIN_BUTTON_LABEL = "Log in";
    public static final String SWITCH_VIEW_LABEL = "Switch to Sign up view";
    private LogInState state = new LogInState();

    public LogInViewModel() { super("Log in"); }

    public void setState(LogInState state) {
        this.state = state;
    }

    public LogInState getState() {
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

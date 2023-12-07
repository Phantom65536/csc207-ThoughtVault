package interface_adapter.log_in_out;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The view model for logging in
 */
public class LogInViewModel extends ViewModel {
    public static final String TITLE_VIEW = "Log in view";
    public static final String USERNAME_LABEL = "Username: ";
    public static final String PASSWORD_LABEL = "Password: ";
    public static final String LOGIN_BUTTON_LABEL = "Log in";
    public static final String SWITCH_VIEW_LABEL = "Switch to Sign up view";
    private LogInState state = new LogInState();

    /**
     * Set the view name (identifier) of this view model for switching view
     */
    public LogInViewModel() { super("Log in"); }

    /**
     * Set the state to a new state
     * @param state
     */
    public void setState(LogInState state) {
        this.state = state;
    }

    /**
     * Get the current state of this view model
     * @return state
     */
    public LogInState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Create an event notification to inform the corresponding LogInView of any change in state
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

}

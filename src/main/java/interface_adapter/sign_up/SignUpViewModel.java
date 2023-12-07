package interface_adapter.sign_up;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The view model for signing up.
 */
public class SignUpViewModel extends ViewModel {
    public static final String TITLE_VIEW = "Log in view";
    public static final String USERNAME_LABEL = "Username: ";
    public static final String PASSWORD_LABEL = "Password: ";
    public static final String REPEAT_PASSWORD_LABEL = "Repeated Password: ";
    public static final String CREDENTIALS_STRING_LABEL = "Credentials String: ";
    public static final String CREDENTIALS_UPLOAD_LABEL = "Upload Credentials File";
    public static final String SIGNUP_BUTTON_LABEL = "Sign up";
    public static final String SWITCH_VIEW_LABEL = "Switch to Log in view";

    private SignUpState state = new SignUpState();

    /**
     * Instantiate a new SignUpState
     */
    public SignUpViewModel() { super("Sign up view"); }

    /**
     * Set current state of this view model
     * @param state
     */
    public void setState(SignUpState state) {
        this.state = state;
    }

    /**
     * Get the current state stored
     * @return state
     */
    public SignUpState getState() {
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

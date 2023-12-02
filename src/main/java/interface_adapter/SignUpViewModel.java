package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SignUpViewModel extends ViewModel {
    public static final String TITLE_VIEW = "Log in view";
    public static final String USERNAME_LABEL = "Username: ";
    public static final String PASSWORD_LABEL = "Password: ";
    public static final String LOGIN_BUTTON_LABEL = "Log in";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    public static final String SWITCH_VIEW_LABEL = "Switch to Sign up view";
    private SignUpState state = new SignUpState();

    public SignUpViewModel() { super("Sign up view"); }

    public void setState(SignUpState state) {
        this.state = state;
    }

    public SignUpState getState() {
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

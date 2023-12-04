package interface_adapter.sign_up;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

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

package interface_adapter.sign_up;

import interface_adapter.ViewManagerModel;
import interface_adapter.log_in_out.LogInState;
import interface_adapter.log_in_out.LogInViewModel;
import use_case.user.SignUpOutputBoundary;

/**
 * Presenter class for signing up
 */
public class SignUpPresenter implements SignUpOutputBoundary {
    private final LogInViewModel logInViewModel;
    private final SignUpViewModel signUpViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Instantiate a new SignUpPresenter which uses to following view models.
     * @param logInViewModel
     * @param signUpViewModel
     * @param viewManagerModel
     */
    public SignUpPresenter(LogInViewModel logInViewModel, SignUpViewModel signUpViewModel, ViewManagerModel viewManagerModel) {
        this.logInViewModel = logInViewModel;
        this.signUpViewModel = signUpViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Switch to log in view when sign up is successful
     * @param username
     */
    @Override
    public void prepareSuccessView(String username) {
        signUpViewModel.setState(new SignUpState());
        signUpViewModel.firePropertyChanged();
        LogInState logInState = logInViewModel.getState();
        logInState.setUsername(username);
        logInViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(logInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Display error messages when sign up fails
     * @param error
     * @param errorType
     */
    @Override
    public void prepareFailView(String error, ErrorType errorType) {
        SignUpState signUpState = signUpViewModel.getState();
        if (errorType == ErrorType.USERNAME) {
            signUpState.setUsernameError(error);
        } else if (errorType == ErrorType.PASSWORD) {
            signUpState.setPasswordError(error);
        } else if (errorType == ErrorType.CREDENTIALS) {
            signUpState.setCredentialsError(error);
        }
        signUpViewModel.firePropertyChanged();
    }

    /**
     * Switch to log in view
     */
    @Override
    public void switchToLogin() {
        signUpViewModel.setState(new SignUpState());
        signUpViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(logInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

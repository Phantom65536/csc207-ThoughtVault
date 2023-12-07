package interface_adapter.log_in_out;

import interface_adapter.sign_up.SignUpViewModel;
import interface_adapter.ViewManagerModel;
import use_case.user.LogInOutOutputBoundary;

/**
 * Presenter class for logging in and out
 */
public class LogInOutPresenter implements LogInOutOutputBoundary {
    private final LogInViewModel logInViewModel;
    private final SignUpViewModel signUpViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Instantiate a new LogInOutPresenter which uses the following view models.
     * @param logInViewModel
     * @param signUpViewModel
     * @param viewManagerModel
     */
    public LogInOutPresenter(LogInViewModel logInViewModel, SignUpViewModel signUpViewModel, ViewManagerModel viewManagerModel) {
        this.logInViewModel = logInViewModel;
        this.signUpViewModel = signUpViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Reset log in state if log in is successful
     * @param userid
     */
    @Override
    public void logInSuccessView(int userid) {
        logInViewModel.setState(new LogInState());
        // switch to events listed view
    }

    /**
     * Display error messages when log in fails.
     * @param errMsg
     * @param errorType
     */
    @Override
    public void prepareFailView(String errMsg, ErrorType errorType) {
        LogInState logInState = logInViewModel.getState();
        if (errorType == ErrorType.USERNAME) {
            logInState.setUsernameError(errMsg);
        } else if (errorType == ErrorType.PASSWORD) {
            logInState.setPasswordError(errMsg);
        }
        logInViewModel.firePropertyChanged();
    }

    /**
     * Switch back to log in view when log out is successful
     */
    @Override
    public void logOutSuccessView() {
        // reset userid in events listed state to null (event listed view model reset state)
        viewManagerModel.setActiveView(logInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Switch to sign up view
     */
    @Override
    public void switchToSignup() {
        logInViewModel.setState(new LogInState());
        logInViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(signUpViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

package interface_adapter.log_in_out;

import interface_adapter.sign_up.SignUpViewModel;
import interface_adapter.ViewManagerModel;
import use_case.user.LogInOutOutputBoundary;

public class LogInOutPresenter implements LogInOutOutputBoundary {
    private final LogInViewModel logInViewModel;
    private final SignUpViewModel signUpViewModel;
    private final ViewManagerModel viewManagerModel;

    public LogInOutPresenter(LogInViewModel logInViewModel, SignUpViewModel signUpViewModel, ViewManagerModel viewManagerModel) {
        this.logInViewModel = logInViewModel;
        this.signUpViewModel = signUpViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void logInSuccessView(int userid) {
        logInViewModel.setState(new LogInState());
        // switch to events listed view
    }

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

    @Override
    public void logOutSuccessView() {
        // reset userid in events listed state to null (event listed view model reset state)
        viewManagerModel.setActiveView(logInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToSignup() {
        logInViewModel.setState(new LogInState());
        logInViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(signUpViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

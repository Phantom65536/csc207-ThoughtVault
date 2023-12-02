package interface_adapter.sign_up;

import interface_adapter.ViewManagerModel;
import interface_adapter.log_in_out.LogInState;
import interface_adapter.log_in_out.LogInViewModel;
import use_case.user.SignUpOutputBoundary;

public class SignUpPresenter implements SignUpOutputBoundary {
    private final LogInViewModel logInViewModel;
    private final SignUpViewModel signUpViewModel;
    private final ViewManagerModel viewManagerModel;

    public SignUpPresenter(LogInViewModel logInViewModel, SignUpViewModel signUpViewModel, ViewManagerModel viewManagerModel) {
        this.logInViewModel = logInViewModel;
        this.signUpViewModel = signUpViewModel;
        this.viewManagerModel = viewManagerModel;
    }
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

    @Override
    public void switchToLogin() {
        signUpViewModel.setState(new SignUpState());
        signUpViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(logInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

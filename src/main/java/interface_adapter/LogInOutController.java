package interface_adapter;

import InputData.LoginInputData;
import use_case.LogInOutInputBoundary;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class LogInOutController {
    final LogInOutInputBoundary logInOutInteractor;

    public LogInOutController(LogInOutInputBoundary logInOutInteractor) {
        this.logInOutInteractor = logInOutInteractor;
    }

    public void logIn(String username, String password) throws GeneralSecurityException, IOException {
        LoginInputData inputData = new LoginInputData(username, password);
        logInOutInteractor.logIn(inputData);
    }

    public void logOut() {
        logInOutInteractor.logOut();
    }

    public void switchToSignupView() {
        logInOutInteractor.switchToSignup();
    }
}

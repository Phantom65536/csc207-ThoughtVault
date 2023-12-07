package interface_adapter.log_in_out;

import use_case.user.LoginInputData;
import use_case.user.LogInOutInputBoundary;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * Controller class for logging in or out.
 */
public class LogInOutController {
    final LogInOutInputBoundary logInOutInteractor;

    /**
     * Set the logInOutInteractor to be used for this controller
     * @param logInOutInteractor
     */
    public LogInOutController(LogInOutInputBoundary logInOutInteractor) {
        this.logInOutInteractor = logInOutInteractor;
    }

    /**
     * Attempts to log into the system.
     * @param username
     * @param password
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public void logIn(String username, String password) throws GeneralSecurityException, IOException {
        LoginInputData inputData = new LoginInputData(username, password);
        logInOutInteractor.logIn(inputData);
    }

    /**
     * Attempts to log out of the system.
     */
    public void logOut() {
        logInOutInteractor.logOut();
    }

    /**
     * Attempts to switch to sign up view
     */
    public void switchToSignupView() {
        logInOutInteractor.switchToSignup();
    }
}

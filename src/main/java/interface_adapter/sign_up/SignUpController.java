package interface_adapter.sign_up;

import use_case.user.SignUpInputData;
import use_case.user.SignUpInputBoundary;

/**
 * Controller class for signing up.
 */
public class SignUpController {
    final SignUpInputBoundary signUpInteractor;

    /**
     * Set the signUpInteractor to be used for this controller
     * @param signUpInteractor
     */
    public SignUpController(SignUpInputBoundary signUpInteractor) {
        this.signUpInteractor = signUpInteractor;
    }

    /**
     * Attempts to sign up a new user
     * @param username
     * @param password
     * @param repeatedPw
     * @param credentialsJSON
     */
    public void signUp(String username, String password, String repeatedPw, String credentialsJSON) {
        SignUpInputData inputData = new SignUpInputData(username, password, repeatedPw, credentialsJSON);
        signUpInteractor.createUser(inputData);
    }

    /**
     * Attempts to switch to log in view
     */
    public void switchToLoginView() {
        signUpInteractor.switchtoLogin();
    }
}

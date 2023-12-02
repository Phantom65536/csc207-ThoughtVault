package interface_adapter.sign_up;

import input_data.SignUpInputData;
import use_case.user.SignUpInputBoundary;

public class SignUpController {
    final SignUpInputBoundary signUpInteractor;

    public SignUpController(SignUpInputBoundary signUpInteractor) {
        this.signUpInteractor = signUpInteractor;
    }

    public void signUp(String username, String password, String repeatedPw, String credentialsJSON) {
        SignUpInputData inputData = new SignUpInputData(username, password, repeatedPw, credentialsJSON);
        signUpInteractor.createUser(inputData);
    }

    public void switchToLoginView() {
        signUpInteractor.switchtoLogin();
    }
}

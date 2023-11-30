package interface_adapter;

import InputData.SignUpInputData;
import use_case.SignUpInputBoundary;

public class SignUpController {
    final SignUpInputBoundary signUpInteractor;

    public SignUpController(SignUpInputBoundary signUpInteractor) {
        this.signUpInteractor = signUpInteractor;
    }

    public void signUp(String username, String password, String repeatedPw, String credentialsJSON) {
        SignUpInputData inputData = new SignUpInputData(username, password, repeatedPw, credentialsJSON);
        signUpInteractor.createUser(inputData);
    }
}

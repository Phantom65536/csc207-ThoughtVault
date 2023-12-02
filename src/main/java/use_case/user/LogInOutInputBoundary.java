package use_case.user;

import input_data.LoginInputData;

import java.io.IOException;

public interface LogInOutInputBoundary {
    void logIn(LoginInputData inputData) throws java.security.GeneralSecurityException, IOException;
    void logOut();
    void switchToSignup();
}

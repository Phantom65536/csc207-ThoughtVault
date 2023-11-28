package use_case;

import InputData.LoginInputData;

import java.io.IOException;

public interface LogInOutInputBoundary {
    void logIn(LoginInputData inputData) throws java.security.GeneralSecurityException, IOException;
    void logOut();
}

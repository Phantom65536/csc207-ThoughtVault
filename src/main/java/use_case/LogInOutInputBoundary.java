package use_case;

import use_case.note.LoginInputData;

import java.io.IOException;

public interface LogInOutInputBoundary {
    void logIn(LoginInputData inputData) throws java.security.GeneralSecurityException, IOException;
    void logOut();
}

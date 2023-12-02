package use_case.user;

import java.io.IOException;

public interface LogInOutInputBoundary {
    void logIn(LoginInputData inputData) throws java.security.GeneralSecurityException, IOException;
    void logOut();
    void switchToSignup();
}

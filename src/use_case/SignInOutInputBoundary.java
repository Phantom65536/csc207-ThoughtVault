package use_case;

import InputData.LoginInputData;
import OutputData.UserOutputData;

public interface SignInOutInputBoundary {
    UserOutputData logIn(LoginInputData inputData);
    boolean logOut();
}

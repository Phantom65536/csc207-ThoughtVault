package use_case;

public interface SignInOutInputBoundary {
    UserOutputData logIn(LoginInputData inputData);
    boolean logOut();
}

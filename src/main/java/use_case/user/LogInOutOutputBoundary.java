package use_case.user;

public interface LogInOutOutputBoundary {
    enum ErrorType {USERNAME, PASSWORD};
    void logInSuccessView(int userid);
    void prepareFailView(String errMsg, ErrorType errorType);
    void logOutSuccessView();           // set userid in event listed ViewModel state to be null
    void switchToSignup();
}

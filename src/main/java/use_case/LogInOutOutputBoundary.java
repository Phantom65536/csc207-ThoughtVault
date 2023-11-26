package use_case;

public interface LogInOutOutputBoundary {
    void logInSuccessView(int userid);
    void prepareFailView(String errMsg);
    void logOutSuccessView();           // set userid in event listed ViewModel state to be null
}

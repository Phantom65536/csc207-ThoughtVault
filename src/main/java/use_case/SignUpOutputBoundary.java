package use_case;

public interface SignUpOutputBoundary {
    enum ErrorType {USERNAME, PASSWORD, CREDENTIALS};
    void prepareSuccessView(String username);
    void prepareFailView(String error, ErrorType errorType);
    void switchToLogin();
}

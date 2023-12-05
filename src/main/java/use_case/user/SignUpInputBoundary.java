package use_case.user;

public interface SignUpInputBoundary {
    void createUser(SignUpInputData inputData);
    void switchtoLogin();
}

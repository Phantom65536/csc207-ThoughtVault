package use_case.user;

import input_data.SignUpInputData;

public interface SignUpInputBoundary {
    void createUser(SignUpInputData inputData);
    void switchtoLogin();
}

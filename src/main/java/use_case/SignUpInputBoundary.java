package use_case;

import InputData.SignUpInputData;

public interface SignUpInputBoundary {
    void createUser(SignUpInputData inputData);
}

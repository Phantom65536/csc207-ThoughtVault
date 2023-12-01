package use_case;

import InputData.SignUpInputData;

public interface signUpInputBoundary {
    boolean createUser(SignUpInputData inputData);
}

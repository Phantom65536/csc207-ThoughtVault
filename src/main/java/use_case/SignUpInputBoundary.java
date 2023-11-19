package use_case;

import InputData.SignUpInputData;

public interface SignUpInputBoundary {
    boolean createUser(SignUpInputData inputData);
}

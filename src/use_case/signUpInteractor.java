package use_case;

import InputData.SignUpInputData;

public interface signUpInteractor {
    boolean createUser(SignUpInputData inputData);
}

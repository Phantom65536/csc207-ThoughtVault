package use_case.user;

import org.mindrot.jbcrypt.BCrypt;
import data_access.GCalDataAccessObject;
import entity.User;

public class SignUpInteractor implements SignUpInputBoundary{
    final UserDataAccessInterface userDataAccessObject;
    final SignUpOutputBoundary signUpPresenter;

    public SignUpInteractor(UserDataAccessInterface userDataAccessObject, SignUpOutputBoundary signUpPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.signUpPresenter = signUpPresenter;
    }

    @Override
    public void createUser(SignUpInputData inputData) {
        if (userDataAccessObject.getUserByUsername(inputData.getUsername()) != null) {
            signUpPresenter.prepareFailView("This username is already taken.", SignUpOutputBoundary.ErrorType.USERNAME);
        } else if (!inputData.getPassword().equals(inputData.getRepeatPassword())) {
            signUpPresenter.prepareFailView("The password you entered does not match your repeated password.", SignUpOutputBoundary.ErrorType.PASSWORD);
        } else if (GCalDataAccessObject.getCredentials(inputData.getAPIKEy()) == null) {
            signUpPresenter.prepareFailView("Invalid credentials.", SignUpOutputBoundary.ErrorType.CREDENTIALS);
        } else {
            String hashedPw = hashPassword(inputData.getPassword());
            User newUser = new User(userDataAccessObject.getNewID(), inputData.getUsername(), hashedPw, inputData.getAPIKEy());
            userDataAccessObject.save(newUser);
            signUpPresenter.prepareSuccessView(newUser.getUsername());
        }
    }

    @Override
    public void switchtoLogin() {
        signUpPresenter.switchToLogin();
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}

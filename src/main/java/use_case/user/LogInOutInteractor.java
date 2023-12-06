package use_case.user;

import com.google.api.client.auth.oauth2.Credential;
import data_access.GCalDataAccessObject;
import entity.User;
import org.mindrot.jbcrypt.BCrypt;
import use_case.gcalevent.GCalEventDataAccessInterface;

import java.io.IOException;

public class LogInOutInteractor implements LogInOutInputBoundary{
    final UserDataAccessInterface userDataAccessObject;
    final GCalEventDataAccessInterface gcalDAO;
    final LogInOutOutputBoundary logInOutPresenter;

    public LogInOutInteractor(UserDataAccessInterface userDataAccessObject, GCalEventDataAccessInterface gcalDAO, LogInOutOutputBoundary logInOutPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.gcalDAO = gcalDAO;
        this.logInOutPresenter = logInOutPresenter;
    }

    @Override
    public void logIn(LoginInputData inputData) throws java.security.GeneralSecurityException, IOException {
        User currUser = userDataAccessObject.getUserByUsername(inputData.getUsername());
        if (currUser == null) {
            logInOutPresenter.prepareFailView("This user does not exist.", LogInOutOutputBoundary.ErrorType.USERNAME);
        } else if (!comparePw(inputData.getPassword(), currUser.getHashedPassword())) {
            logInOutPresenter.prepareFailView("Password incorrect.", LogInOutOutputBoundary.ErrorType.PASSWORD);
        } else {
            Credential retrievedCred = GCalDataAccessObject.getCredentials(currUser.getCredential());
            gcalDAO.setUserCalendar(retrievedCred);
            logInOutPresenter.logInSuccessView(currUser.getUserid());
        }
    }

    @Override
    public void logOut() {
        gcalDAO.resetUserCalendar();
        logInOutPresenter.logOutSuccessView();
    }

    @Override
    public void switchToSignup() {
        logInOutPresenter.switchToSignup();
    }

    private boolean comparePw(String enteredPw, String hashedPw) {
        return BCrypt.checkpw(enteredPw, hashedPw);
    }
}

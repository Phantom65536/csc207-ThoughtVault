package use_case.user;

import com.google.api.client.auth.oauth2.Credential;
import data_access.GCalDataAccessObject;
import entity.User;
import org.mindrot.jbcrypt.BCrypt;
import use_case.gcalevent.GCalEventDataAccessInterface;

import java.io.IOException;

/**
 * An interactor class for logging in and out.
 */
public class LogInOutInteractor implements LogInOutInputBoundary{
    final UserDataAccessInterface userDataAccessObject;
    final GCalEventDataAccessInterface gcalDAO;
    final LogInOutOutputBoundary logInOutPresenter;

    /**
     * Instantiate a LogInOutInteractor with the following DAOs and presenter.
     * @param userDataAccessObject
     * @param gcalDAO
     * @param logInOutPresenter
     */
    public LogInOutInteractor(UserDataAccessInterface userDataAccessObject, GCalEventDataAccessInterface gcalDAO, LogInOutOutputBoundary logInOutPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.gcalDAO = gcalDAO;
        this.logInOutPresenter = logInOutPresenter;
    }

    /**
     * Log into the system and check for any errors. Errors are thrown by GCalDAO.
     * @param inputData
     * @throws java.security.GeneralSecurityException
     * @throws IOException
     */
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

    /**
     * Log out of the system.
     */
    @Override
    public void logOut() {
        gcalDAO.resetUserCalendar();
        logInOutPresenter.logOutSuccessView();
    }

    /**
     * Switch to sign up view.
     */
    @Override
    public void switchToSignup() {
        logInOutPresenter.switchToSignup();
    }

    private boolean comparePw(String enteredPw, String hashedPw) {
        return BCrypt.checkpw(enteredPw, hashedPw);
    }
}

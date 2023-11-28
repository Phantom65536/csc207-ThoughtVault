package use_case;

import InputData.LoginInputData;
import com.google.api.client.auth.oauth2.Credential;
import data_access.GCalDataAccessObject;
import entity.User;

import java.io.IOException;

public class LogInOutInteractor implements LogInOutInputBoundary{
    final UserDataAccessInterface userDataAccessObject;
    final GCalDataAccessObject gcalDAO;
    final LogInOutOutputBoundary logInOutPresenter;

    public LogInOutInteractor(UserDataAccessInterface userDataAccessObject, GCalDataAccessObject gcalDAO, LogInOutOutputBoundary logInOutPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.gcalDAO = gcalDAO;
        this.logInOutPresenter = logInOutPresenter;
    }

    @Override
    public void logIn(LoginInputData inputData) throws java.security.GeneralSecurityException, IOException {
        User currUser = userDataAccessObject.getUserByUsername(inputData.getUsername());
        if (currUser == null) {
            logInOutPresenter.prepareFailView("This user does not exist.");
        } else if (!SignUpInteractor.hashPassword(inputData.getPassword()).equals(currUser.getHashedPassword())) {
            logInOutPresenter.prepareFailView("Password incorrect.");
        }
        Credential retrievedCred = GCalDataAccessObject.getCredentials(currUser.getCredential());
        gcalDAO.setUserCalendar(retrievedCred);
        logInOutPresenter.logInSuccessView(currUser.getUserid());
    }

    @Override
    public void logOut() {
        // potentially reset gcalDAO's cal to null
        logInOutPresenter.logOutSuccessView();
    }
}

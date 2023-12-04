package use_case.user;

import data_access.GCalDataAccessObject;
import data_access.UserDataAccessObject;
import junit.framework.TestCase;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

public class LogInOutInteractorTest {

    String username = "Elsie";
    String password = "password";
    @Test
    public void logInSuccerssTest() throws IOException, ParseException, GeneralSecurityException {
        UserDataAccessInterface userDataAccessObject = new UserDataAccessObject("src/test/resources/users.json");
        GCalDataAccessObject gcalDAO = new GCalDataAccessObject();
        LogInOutOutputBoundary logInOutPresenter = new LogInOutOutputBoundary(){
            @Override
            public void logInSuccessView(int userid){
                assertEquals(userid, 1);
            };
            @Override
            public void prepareFailView(String errMsg, LogInOutOutputBoundary.ErrorType errorType){
                fail("Use case failure is unexpected.");
            };

            @Override
            public void logOutSuccessView(){
                fail("Use case logout is unexpected.");
            };

            public void switchToSignup(){
                fail("Use case sign up is unexpected.");
            }


        };
        LogInOutInteractor interactor = new LogInOutInteractor(userDataAccessObject, gcalDAO, logInOutPresenter);
        LoginInputData inputData = new LoginInputData(username, password);
        interactor.logIn(inputData);
    }

    @Test
    public void logInFailUserNotExistTest() throws IOException, ParseException, GeneralSecurityException {
        UserDataAccessInterface userDataAccessObject = new UserDataAccessObject("src/test/resources/users.json");
        GCalDataAccessObject gcalDAO = new GCalDataAccessObject();
        LogInOutOutputBoundary logInOutPresenter = new LogInOutOutputBoundary(){
            @Override
            public void logInSuccessView(int userid){
                fail("Use case success is unexpected.");
            };
            @Override
            public void prepareFailView(String errMsg, LogInOutOutputBoundary.ErrorType errorType){
                assertEquals(errMsg,"This user does not exist.");
                assertEquals(errorType,LogInOutOutputBoundary.ErrorType.USERNAME);
            };

            @Override
            public void logOutSuccessView(){
                fail("Use case logout is unexpected.");
            };

            public void switchToSignup(){
                fail("Use case sign up is unexpected.");
            }


        };
        LogInOutInteractor interactor = new LogInOutInteractor(userDataAccessObject, gcalDAO, logInOutPresenter);
        LoginInputData inputData = new LoginInputData("", password);
        interactor.logIn(inputData);
    }

    @Test
    public void logInFailIncorrectPasswordTest() throws IOException, ParseException, GeneralSecurityException {
        UserDataAccessInterface userDataAccessObject = new UserDataAccessObject("src/test/resources/users.json");
        GCalDataAccessObject gcalDAO = new GCalDataAccessObject();
        LogInOutOutputBoundary logInOutPresenter = new LogInOutOutputBoundary(){
            @Override
            public void logInSuccessView(int userid){
                fail("Use case success is unexpected.");
            };
            @Override
            public void prepareFailView(String errMsg, LogInOutOutputBoundary.ErrorType errorType){
                assertEquals(errMsg,"Password incorrect.");
                assertEquals(errorType,LogInOutOutputBoundary.ErrorType.PASSWORD);
            };

            @Override
            public void logOutSuccessView(){
                fail("Use case logout is unexpected.");
            };

            public void switchToSignup(){
                fail("Use case sign up is unexpected.");
            }


        };
        LogInOutInteractor interactor = new LogInOutInteractor(userDataAccessObject, gcalDAO, logInOutPresenter);
        LoginInputData inputData = new LoginInputData(username, "wrong");
        interactor.logIn(inputData);
    }

    @Test
    public void logOut() throws IOException, ParseException, GeneralSecurityException {
        UserDataAccessInterface userDataAccessObject = new UserDataAccessObject("src/test/resources/users.json");
        GCalDataAccessObject gcalDAO = new GCalDataAccessObject();
        LogInOutOutputBoundary logInOutPresenter = new LogInOutOutputBoundary(){
            @Override
            public void logInSuccessView(int userid){
                assertEquals(userid, 1);
            };
            @Override
            public void prepareFailView(String errMsg, LogInOutOutputBoundary.ErrorType errorType){
                fail("Use case failure is unexpected.");
            };

            @Override
            public void logOutSuccessView(){
                assertNotNull(1);
            };

            public void switchToSignup(){
                fail("Use case sign up is unexpected.");
            }


        };
        LogInOutInteractor interactor = new LogInOutInteractor(userDataAccessObject, gcalDAO, logInOutPresenter);
        LoginInputData inputData = new LoginInputData(username, password);
        interactor.logIn(inputData);
        assertNotNull(gcalDAO.getCalendar());
        assertNotNull(gcalDAO.getCalendarId());
        interactor.logOut();
        assertNull(gcalDAO.getCalendar());
        assertNull(gcalDAO.getCalendarId());
    }

    @Test
    public void switchToSignup() throws IOException, ParseException {
        UserDataAccessInterface userDataAccessObject = new UserDataAccessObject("src/test/resources/users.json");
        GCalDataAccessObject gcalDAO = new GCalDataAccessObject();
        LogInOutOutputBoundary logInOutPresenter = new LogInOutOutputBoundary(){
            @Override
            public void logInSuccessView(int userid){
                fail("Use case success is unexpected.");
            };
            @Override
            public void prepareFailView(String errMsg, LogInOutOutputBoundary.ErrorType errorType){
                fail("Use case failure is unexpected.");
            };

            @Override
            public void logOutSuccessView(){
                fail("Use case logout is unexpected.");
            };

            public void switchToSignup(){
                assertNotNull(1);
            }


        };
        LogInOutInteractor interactor = new LogInOutInteractor(userDataAccessObject, gcalDAO, logInOutPresenter);
        LoginInputData inputData = new LoginInputData(username, password);
        interactor.switchToSignup();
    }
}
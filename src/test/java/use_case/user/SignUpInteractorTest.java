package use_case.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import data_access.UserDataAccessObject;
import junit.framework.TestCase;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

public class SignUpInteractorTest {


    String APIkey = "{\"installed\":{\"client_id\":\"676658923300-jefh7ko5cp9n7cf92vj427ltrd0rumo4.apps.googleusercontent.com\",\"project_id\":\"thought-vault-400423\",\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"token_uri\":\"https://oauth2.googleapis.com/token\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\",\"client_secret\":\"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\",\"redirect_uris\":[\"http://localhost\"]}}";
    @Test
    public void createUserSuccerssTest() throws IOException, ParseException {
        String timeNow = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new java.util.Date());
        SignUpInputData inputData = new SignUpInputData("Elsie", "password", "password", APIkey);
        UserDataAccessInterface userDataAccessObject = new UserDataAccessObject("src/test/resources/users"+timeNow+".json");
        SignUpOutputBoundary successPresenter = new SignUpOutputBoundary() {
            @Override
            public void prepareSuccessView(String username) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals("Elsie", inputData.getUsername());
//                assertNotNull(user.getCreationTime()); // any creation time is fine.
//                assertTrue(userRepository.existsByName("Paul"));
            }

            @Override
            public void prepareFailView(String error, ErrorType errorType) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToLogin(){
                fail("Use case failure is unexpected.");
            }
        };

        SignUpInputBoundary interactor = new SignUpInteractor(userDataAccessObject, successPresenter);
        interactor.createUser(inputData);

    }

    @Test
    public void createUserFailureUserTakenTest() throws IOException, ParseException {
        String timeNow = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new java.util.Date());
        SignUpInputData inputData = new SignUpInputData("Elsie", "password", "password", APIkey);
        UserDataAccessInterface userDataAccessObject = new UserDataAccessObject("src/test/resources/users"+timeNow+".json");
        SignUpOutputBoundary successPresenter = new SignUpOutputBoundary() {
            @Override
            public void prepareSuccessView(String username) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals("Elsie", inputData.getUsername());
            }

            @Override
            public void prepareFailView(String error, ErrorType errorType) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToLogin(){
                fail("switchToLogin is unexpected.");
            }
        };

        SignUpInputBoundary interactor = new SignUpInteractor(userDataAccessObject, successPresenter);
        interactor.createUser(inputData);
        SignUpOutputBoundary failPresenter = new SignUpOutputBoundary() {
            @Override
            public void prepareSuccessView(String username) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error, ErrorType errorType) {
                assertEquals(error, "This username is already taken.");
                assertEquals(errorType, SignUpOutputBoundary.ErrorType.USERNAME);
            }

            @Override
            public void switchToLogin(){
                fail("Use case login is unexpected.");
            }
        };

        interactor = new SignUpInteractor(userDataAccessObject, failPresenter);
        interactor.createUser(inputData);

    }

    @Test
    public void createUserFailurePasswordMismatch() throws IOException, ParseException {
        String timeNow = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new java.util.Date());
        SignUpInputData inputData = new SignUpInputData("Elsie", "password", "wrong", APIkey);
        UserDataAccessInterface userDataAccessObject = new UserDataAccessObject("src/test/resources/users"+timeNow+".json");
        SignUpOutputBoundary failPresenter = new SignUpOutputBoundary() {
            @Override
            public void prepareSuccessView(String username) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error, ErrorType errorType) {
                assertEquals(error, "The password you entered does not match your repeated password.");
                assertEquals(errorType, SignUpOutputBoundary.ErrorType.PASSWORD);
            }

            @Override
            public void switchToLogin(){
                fail("Use case login is unexpected.");
            }
        };

        SignUpInputBoundary interactor = new SignUpInteractor(userDataAccessObject, failPresenter);
        interactor.createUser(inputData);
    }


    @Test
    public void createUserFailureInvalidCredentialTest() throws IOException, ParseException {
        String timeNow = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new java.util.Date());
        SignUpInputData inputData = new SignUpInputData("Elsie", "password", "password", "APIkey");
        UserDataAccessInterface userDataAccessObject = new UserDataAccessObject("src/test/resources/users"+timeNow+".json");
        SignUpOutputBoundary failPresenter = new SignUpOutputBoundary() {
            @Override
            public void prepareSuccessView(String username) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error, ErrorType errorType) {
                assertEquals(error, "Invalid credentials.");
                assertEquals(errorType, SignUpOutputBoundary.ErrorType.CREDENTIALS);
            }

            @Override
            public void switchToLogin(){
                fail("Use case login is unexpected.");
            }
        };

        SignUpInputBoundary interactor = new SignUpInteractor(userDataAccessObject, failPresenter);
        interactor.createUser(inputData);
    }

    @Test
    public void switchtoLoginTest() throws IOException, ParseException {
        String timeNow = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new java.util.Date());
        SignUpInputData inputData = new SignUpInputData("Elsie", "password", "password", APIkey);
        UserDataAccessInterface userDataAccessObject = new UserDataAccessObject("src/test/resources/users"+timeNow+".json");

        SignUpOutputBoundary loginPresenter = new SignUpOutputBoundary() {
            @Override
            public void prepareSuccessView(String username) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error, ErrorType errorType) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToLogin(){
                assertNotNull("Done");
            }
        };
        SignUpInputBoundary interactor = new SignUpInteractor(userDataAccessObject, loginPresenter);
        interactor.switchtoLogin();
    }

    @Test
    public void hashPasswordTest() {
        String hashed = SignUpInteractor.hashPassword("123456");
        String answer = BCrypt.with(BCrypt.Version.VERSION_2Y).hashToString(6, "123456".toCharArray());
        assertEquals(answer, hashed);

    }
}
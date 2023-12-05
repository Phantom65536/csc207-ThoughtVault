package view.user;

import data_access.GCalDataAccessObject;
import data_access.UserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.log_in_out.*;
import interface_adapter.log_in_out.LogInOutPresenter;
import interface_adapter.log_in_out.LogInState;
import interface_adapter.sign_up.SignUpViewModel;
import junit.framework.TestCase;
import org.json.simple.parser.ParseException;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.junit.MockitoJUnitRunner;
import use_case.user.*;
import view.LabelTextPanel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LogInViewTest extends TestCase {
    String username = "Elsie";
    String password = "password";
    String repeatPassword = "password";
    String credential = "{\"installed\":{\"client_id\":\"676658923300-jefh7ko5cp9n7cf92vj427ltrd0rumo4.apps.googleusercontent.com\",\"project_id\":\"thought-vault-400423\",\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"token_uri\":\"https://oauth2.googleapis.com/token\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\",\"client_secret\":\"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\",\"redirect_uris\":[\"http://localhost\"]}}";

    @org.junit.Test
    public void testUsernameInfo() {

        // create the UI; note, we don't make a real LogInOutInputBoundary,
        // since we don't need it for this test.
        LogInOutInputBoundary sib = null;

        LogInOutController controller = new LogInOutController(sib);
        LogInViewModel viewModel = new LogInViewModel();
        JPanel LogInView = new LogInView(viewModel, controller);
        JFrame jf = new JFrame();
        jf.setContentPane(LogInView);
        jf.pack();
        jf.setVisible(true);

        // get a reference to the first password field
        LabelTextPanel usernameInfoPanel = (LabelTextPanel) LogInView.getComponent(1);
        JTextField usernameField = (JTextField) usernameInfoPanel.getComponent(1);

        // create and dispatch KeyEvents to the UI
        KeyEvent event = new KeyEvent(
                usernameField, // we are interacting with the pwdField
                KeyEvent.KEY_TYPED, //
                System.currentTimeMillis(), // say the event happened right now
                0, // no modifiers
                KeyEvent.VK_UNDEFINED, // for KEY_TYPED, the KeyCode is undefined per documentation
                'y'); // the character that is being typed

        usernameInfoPanel.dispatchEvent(event);


        // pause execution for a second
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // print the current values the password field and view-model hold
        System.out.println("field 1: " + new String(usernameField.getText()));
        System.out.println("view-model: " + viewModel.getState().getUsername());

        // move to the right in the password field, otherwise the event
        // will type the character as the first character instead of the last!
        KeyEvent eventRight = new KeyEvent(
                usernameField,
                KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_RIGHT,
                KeyEvent.CHAR_UNDEFINED
        );
        usernameInfoPanel.dispatchEvent(eventRight);
        

        // pause execution for a second
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // type a second character
        KeyEvent event2 = new KeyEvent(
                usernameField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'z');
        usernameInfoPanel.dispatchEvent(event2);


        // pause execution for 3 seconds
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // print the current values the password field and view-model hold
        System.out.println("field 1: " + new String(usernameField.getText()));
        System.out.println("view-model: " + viewModel.getState().getUsername());

        // assert that the values are as expected.
        assertEquals("yz", new String(usernameField.getText()));
        assertEquals("yz", viewModel.getState().getUsername());
    }

    @org.junit.Test
    public void testPasswordInfo() {

        // create the UI; note, we don't make a real LogInOutInputBoundary,
        // since we don't need it for this test.
        LogInOutInputBoundary sib = null;
        LogInOutController controller = new LogInOutController(sib);
        LogInViewModel viewModel = new LogInViewModel();
        JPanel LogInView = new LogInView(viewModel, controller);
        JFrame jf = new JFrame();
        jf.setContentPane(LogInView);
        jf.pack();
        jf.setVisible(true);

        // get a reference to the first password field
        LabelTextPanel passwordInfoPanel = (LabelTextPanel) LogInView.getComponent(2);
        JPasswordField pwdField = (JPasswordField) passwordInfoPanel.getComponent(1);

        // create and dispatch KeyEvents to the UI
        KeyEvent event = new KeyEvent(
                pwdField, // we are interacting with the pwdField
                KeyEvent.KEY_TYPED, //
                System.currentTimeMillis(), // say the event happened right now
                0, // no modifiers
                KeyEvent.VK_UNDEFINED, // for KEY_TYPED, the KeyCode is undefined per documentation
                'y'); // the character that is being typed

        passwordInfoPanel.dispatchEvent(event);


        // pause execution for a second
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // print the current values the password field and view-model hold
        System.out.println("field 1: " + new String(pwdField.getPassword()));
        System.out.println("view-model: " + viewModel.getState().getPassword());

        // move to the right in the password field, otherwise the event
        // will type the character as the first character instead of the last!
        KeyEvent eventRight = new KeyEvent(
                pwdField,
                KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_RIGHT,
                KeyEvent.CHAR_UNDEFINED
        );
        passwordInfoPanel.dispatchEvent(eventRight);

        // pause execution for a second
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // type a second character
        KeyEvent event2 = new KeyEvent(
                pwdField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'z');
        passwordInfoPanel.dispatchEvent(event2);


        // pause execution for 3 seconds
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // print the current values the password field and view-model hold
        System.out.println("field 1: " + new String(pwdField.getPassword()));
        System.out.println("view-model: " + viewModel.getState().getPassword());

        // assert that the values are as expected.
        assertEquals("yz", new String(pwdField.getPassword()));
        assertEquals("yz", viewModel.getState().getPassword());
    }
    
    @org.junit.Test
    public void testSwitchToSignupView() throws IOException, ParseException {

        // create the UI; note, we don't make a real LogInOutInputBoundary,
        // since we don't need it for this test.
        UserDataAccessInterface userDataAccessObject = new UserDataAccessObject("src/test/resources/users"+".json");

        SignUpViewModel viewModel = new SignUpViewModel();
        LogInViewModel loginviewModel = new LogInViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LogInOutOutputBoundary successPresenter = new LogInOutPresenter(loginviewModel,viewModel,viewManagerModel);

        GCalDataAccessObject gcalDAO = new GCalDataAccessObject();
        

        // create the UI; note, we don't make a real LogInOutInputBoundary,
        // since we don't need it for this test.
        LogInOutInputBoundary interactor = new LogInOutInteractor(userDataAccessObject, gcalDAO, successPresenter);

        LogInOutController controller = spy(new LogInOutController(interactor));


        JPanel LogInView = new LogInView(loginviewModel, controller);

        LogInState currentState = loginviewModel.getState();
        currentState.setUsername(username);
        currentState.setPassword(password);

        // get a reference to the first password field
        JPanel buttonPanel = (JPanel) LogInView.getComponent(3);
        JButton signUpButton = (JButton) buttonPanel.getComponent(1);

        signUpButton.doClick();

        // pause execution for a second
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        verify(controller, times(1)).switchToSignupView();
    }

    @org.junit.Test
    public void testSignIn() throws GeneralSecurityException, IOException {

        // create the UI; note, we don't make a real LogInOutInputBoundary,
        // since we don't need it for this test.
        LogInOutInputBoundary sib = mock(LogInOutInputBoundary.class, Answers.CALLS_REAL_METHODS);

        LogInOutController controller = spy(new LogInOutController(sib));

        LogInViewModel viewModel = new LogInViewModel();
        JPanel LogInView = new LogInView(viewModel, controller);
        JFrame jf = new JFrame();
        jf.setContentPane(LogInView);
        jf.pack();
        jf.setVisible(true);

        // get a reference to the first password field
        LabelTextPanel usernameInfoPanel = (LabelTextPanel) LogInView.getComponent(1);
        JTextField usernameField = (JTextField) usernameInfoPanel.getComponent(1);

        // create and dispatch KeyEvents to the UI
        KeyEvent event = new KeyEvent(
                usernameField, // we are interacting with the pwdField
                KeyEvent.KEY_TYPED, //
                System.currentTimeMillis(), // say the event happened right now
                0, // no modifiers
                KeyEvent.VK_UNDEFINED, // for KEY_TYPED, the KeyCode is undefined per documentation
                'y'); // the character that is being typed

        usernameInfoPanel.dispatchEvent(event);


        // pause execution for a second
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // get a reference to the first password field
        LabelTextPanel passwordInfoPanel = (LabelTextPanel) LogInView.getComponent(2);
        JPasswordField pwdField = (JPasswordField) passwordInfoPanel.getComponent(1);

        // create and dispatch KeyEvents to the UI
        KeyEvent passwordEvent = new KeyEvent(
                pwdField, // we are interacting with the pwdField
                KeyEvent.KEY_TYPED, //
                System.currentTimeMillis(), // say the event happened right now
                0, // no modifiers
                KeyEvent.VK_UNDEFINED, // for KEY_TYPED, the KeyCode is undefined per documentation
                'y'); // the character that is being typed

        passwordInfoPanel.dispatchEvent(passwordEvent);


        // pause execution for a second
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        // get a reference to the first password field
        JPanel buttonPanel = (JPanel) LogInView.getComponent(3);
        JButton signUpButton = (JButton) buttonPanel.getComponent(0);

        signUpButton.doClick();


        // pause execution for a second
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        LogInState currentState = viewModel.getState();
        verify(controller, times(1)).logIn(
                "y",
                "y"
        );

    }

    @org.junit.Test
    public void testSignUpFailed() throws GeneralSecurityException, IOException {

        // create the UI; note, we don't make a real LogInOutInputBoundary,
        // since we don't need it for this test.
        LogInOutInputBoundary sib = mock(LogInOutInputBoundary.class, Answers.CALLS_REAL_METHODS);

        LogInOutController controller = spy(new LogInOutController(sib));

        LogInViewModel viewModel = new LogInViewModel();
        JPanel LogInView = new LogInView(viewModel, controller);
        JFrame jf = new JFrame();
        jf.setContentPane(LogInView);
        jf.pack();
        jf.setVisible(true);

        // get a reference to the first password field
        LabelTextPanel usernameInfoPanel = (LabelTextPanel) LogInView.getComponent(1);
        JTextField usernameField = (JTextField) usernameInfoPanel.getComponent(1);

        // create and dispatch KeyEvents to the UI
        KeyEvent event = new KeyEvent(
                usernameField, // we are interacting with the pwdField
                KeyEvent.KEY_TYPED, //
                System.currentTimeMillis(), // say the event happened right now
                0, // no modifiers
                KeyEvent.VK_UNDEFINED, // for KEY_TYPED, the KeyCode is undefined per documentation
                'y'); // the character that is being typed

        usernameInfoPanel.dispatchEvent(event);


        // pause execution for a second
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // get a reference to the first password field
        LabelTextPanel passwordInfoPanel = (LabelTextPanel) LogInView.getComponent(2);
        JPasswordField pwdField = (JPasswordField) passwordInfoPanel.getComponent(1);

        // create and dispatch KeyEvents to the UI
        KeyEvent passwordEvent = new KeyEvent(
                pwdField, // we are interacting with the pwdField
                KeyEvent.KEY_TYPED, //
                System.currentTimeMillis(), // say the event happened right now
                0, // no modifiers
                KeyEvent.VK_UNDEFINED, // for KEY_TYPED, the KeyCode is undefined per documentation
                'y'); // the character that is being typed

        passwordInfoPanel.dispatchEvent(passwordEvent);


        // pause execution for a second
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // get a reference to the first password field
        LabelTextPanel repeatPasswordInfoPanel = (LabelTextPanel) LogInView.getComponent(2);
        JPasswordField repeatPasswordField = (JPasswordField) repeatPasswordInfoPanel.getComponent(1);

        // create and dispatch KeyEvents to the UI
        KeyEvent repeatPasswordEvent = new KeyEvent(
                repeatPasswordField, // we are interacting with the pwdField
                KeyEvent.KEY_TYPED, //
                System.currentTimeMillis(), // say the event happened right now
                0, // no modifiers
                KeyEvent.VK_UNDEFINED, // for KEY_TYPED, the KeyCode is undefined per documentation
                'y'); // the character that is being typed

        repeatPasswordInfoPanel.dispatchEvent(repeatPasswordEvent);


        // pause execution for a second
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // get a reference to the first password field
        JPanel buttonPanel = (JPanel) LogInView.getComponent(3);
        JButton signUpButton = (JButton) buttonPanel.getComponent(0);

        signUpButton.doClick();


        // pause execution for a second
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        LogInState currentState = viewModel.getState();
        verify(controller, times(0)).logIn(
                "y",
                "y"
        );

    }


    @org.junit.Test
    public void testSignUpUsernameError() throws IOException, ParseException {

        // create the UI; note, we don't make a real LogInOutInputBoundary,
        // since we don't need it for this test.
        UserDataAccessInterface userDataAccessObject = new UserDataAccessObject("src/test/resources/users"+".json");

        SignUpViewModel viewModel = new SignUpViewModel();
        LogInViewModel loginviewModel = new LogInViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LogInOutOutputBoundary successPresenter = new LogInOutPresenter(loginviewModel,viewModel,viewManagerModel);

        GCalDataAccessObject gcalDAO = new GCalDataAccessObject();


        // create the UI; note, we don't make a real LogInOutInputBoundary,
        // since we don't need it for this test.
        LogInOutInputBoundary interactor = new LogInOutInteractor(userDataAccessObject, gcalDAO, successPresenter);

        LogInOutController controller = spy(new LogInOutController(interactor));


        JPanel LogInView = new LogInView(loginviewModel, controller);

        LogInState currentState = loginviewModel.getState();
        currentState.setUsername(username+"y");
        currentState.setPassword(password);

        // get a reference to the first password field
        JPanel buttonPanel = (JPanel) LogInView.getComponent(3);
        JButton signUpButton = (JButton) buttonPanel.getComponent(0);

        signUpButton.doClick();

        // pause execution for a second
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        LogInState LogInState = loginviewModel.getState();
        System.out.println("getUsername: " + LogInState.getUsername());
        System.out.println("getPassword: " + LogInState.getPassword());
        assertNotNull(LogInState.getUsernameError());
        assertNull(LogInState.getPasswordError());

    }

    @org.junit.Test
    public void testSignUpPasswordError() throws IOException, ParseException {

        // create the UI; note, we don't make a real LogInOutInputBoundary,
        // since we don't need it for this test.
        UserDataAccessInterface userDataAccessObject = new UserDataAccessObject("src/test/resources/users"+".json");

        SignUpViewModel viewModel = new SignUpViewModel();
        LogInViewModel loginviewModel = new LogInViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LogInOutOutputBoundary successPresenter = new LogInOutPresenter(loginviewModel,viewModel,viewManagerModel);

        GCalDataAccessObject gcalDAO = new GCalDataAccessObject();


        // create the UI; note, we don't make a real LogInOutInputBoundary,
        // since we don't need it for this test.
        LogInOutInputBoundary interactor = new LogInOutInteractor(userDataAccessObject, gcalDAO, successPresenter);

        LogInOutController controller = spy(new LogInOutController(interactor));


        JPanel LogInView = new LogInView(loginviewModel, controller);

        LogInState currentState = loginviewModel.getState();
        currentState.setUsername(username);
        currentState.setPassword(password+"y");

        // get a reference to the first password field
        JPanel buttonPanel = (JPanel) LogInView.getComponent(3);
        JButton signUpButton = (JButton) buttonPanel.getComponent(0);

        signUpButton.doClick();

        // pause execution for a second
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        LogInState LogInState = loginviewModel.getState();
        System.out.println("getUsername: " + LogInState.getUsername());
        System.out.println("getPassword: " + LogInState.getPassword());
        assertNull(LogInState.getUsernameError());
        assertNotNull(LogInState.getPasswordError());

    }


    @org.junit.Test
    public void testSignUpSuccess() throws IOException, ParseException {
        // create the UI; note, we don't make a real LogInOutInputBoundary,
        // since we don't need it for this test.
        UserDataAccessInterface userDataAccessObject = new UserDataAccessObject("src/test/resources/users"+".json");

        SignUpViewModel viewModel = new SignUpViewModel();
        LogInViewModel loginviewModel = new LogInViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LogInOutOutputBoundary successPresenter = new LogInOutPresenter(loginviewModel,viewModel,viewManagerModel);

        GCalDataAccessObject gcalDAO = new GCalDataAccessObject();


        // create the UI; note, we don't make a real LogInOutInputBoundary,
        // since we don't need it for this test.
        LogInOutInputBoundary interactor = new LogInOutInteractor(userDataAccessObject, gcalDAO, successPresenter);

        LogInOutController controller = spy(new LogInOutController(interactor));


        JPanel LogInView = new LogInView(loginviewModel, controller);

        LogInState currentState = loginviewModel.getState();
        currentState.setUsername(username);
        currentState.setPassword(password);

        // get a reference to the first password field
        JPanel buttonPanel = (JPanel) LogInView.getComponent(3);
        JButton signUpButton = (JButton) buttonPanel.getComponent(0);

        signUpButton.doClick();

        // pause execution for a second
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        LogInState LogInState = loginviewModel.getState();
        System.out.println("getUsername: " + LogInState.getUsername());
        System.out.println("getPassword: " + LogInState.getPassword());
        assertNull(LogInState.getUsernameError());
        assertNull(LogInState.getPasswordError());

    }



}
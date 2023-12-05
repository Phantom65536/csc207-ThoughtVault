package view.user;

import data_access.UserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.log_in_out.LogInViewModel;
import interface_adapter.sign_up.SignUpController;
import interface_adapter.sign_up.SignUpPresenter;
import interface_adapter.sign_up.SignUpState;
import interface_adapter.sign_up.SignUpViewModel;
import junit.framework.TestCase;
import org.json.simple.parser.ParseException;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import use_case.user.*;
import view.LabelTextPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SignUpViewTest extends TestCase {
    String username = "Elsie";
    String password = "password";
    String repeatPassword = "password";
    String credential = "{\"installed\":{\"client_id\":\"676658923300-jefh7ko5cp9n7cf92vj427ltrd0rumo4.apps.googleusercontent.com\",\"project_id\":\"thought-vault-400423\",\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"token_uri\":\"https://oauth2.googleapis.com/token\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\",\"client_secret\":\"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\",\"redirect_uris\":[\"http://localhost\"]}}";

    @org.junit.Test
    public void testUsernameInfo() {

        // create the UI; note, we don't make a real SignupInputBoundary,
        // since we don't need it for this test.
        SignUpInputBoundary sib = null;
        SignUpController controller = new SignUpController(sib);
        SignUpViewModel viewModel = new SignUpViewModel();
        JPanel signupView = new SignUpView(viewModel, controller);
        JFrame jf = new JFrame();
        jf.setContentPane(signupView);
        jf.pack();
        jf.setVisible(true);

        // get a reference to the first password field
        LabelTextPanel usernameInfoPanel = (LabelTextPanel) signupView.getComponent(1);
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
        assertEquals("", viewModel.getState().getPassword());
    }

    @org.junit.Test
    public void testPasswordInfo() {

        // create the UI; note, we don't make a real SignupInputBoundary,
        // since we don't need it for this test.
        SignUpInputBoundary sib = null;
        SignUpController controller = new SignUpController(sib);
        SignUpViewModel viewModel = new SignUpViewModel();
        JPanel signupView = new SignUpView(viewModel, controller);
        JFrame jf = new JFrame();
        jf.setContentPane(signupView);
        jf.pack();
        jf.setVisible(true);

        // get a reference to the first password field
        LabelTextPanel passwordInfoPanel = (LabelTextPanel) signupView.getComponent(2);
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
        assertEquals("", viewModel.getState().getRepeatedPassword());
    }

    @org.junit.Test
    public void testRepeatPasswordInfo() {

        // create the UI; note, we don't make a real SignupInputBoundary,
        // since we don't need it for this test.
        SignUpInputBoundary sib = null;
        SignUpController controller = new SignUpController(sib);
        SignUpViewModel viewModel = new SignUpViewModel();
        JPanel signupView = new SignUpView(viewModel, controller);
        JFrame jf = new JFrame();
        jf.setContentPane(signupView);
        jf.pack();
        jf.setVisible(true);

        // get a reference to the first password field
        LabelTextPanel repeatPasswordInfoPanel = (LabelTextPanel) signupView.getComponent(3);
        JPasswordField pwdField = (JPasswordField) repeatPasswordInfoPanel.getComponent(1);

        // create and dispatch KeyEvents to the UI
        KeyEvent event = new KeyEvent(
                pwdField, // we are interacting with the pwdField
                KeyEvent.KEY_TYPED, //
                System.currentTimeMillis(), // say the event happened right now
                0, // no modifiers
                KeyEvent.VK_UNDEFINED, // for KEY_TYPED, the KeyCode is undefined per documentation
                'y'); // the character that is being typed

        repeatPasswordInfoPanel.dispatchEvent(event);


        // pause execution for a second
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // print the current values the password field and view-model hold
        System.out.println("field 1: " + new String(pwdField.getPassword()));
        System.out.println("view-model: " + viewModel.getState().getRepeatedPassword());

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
        repeatPasswordInfoPanel.dispatchEvent(eventRight);

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
        repeatPasswordInfoPanel.dispatchEvent(event2);


        // pause execution for 3 seconds
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // print the current values the password field and view-model hold
        System.out.println("field 1: " + new String(pwdField.getPassword()));
        System.out.println("view-model: " + viewModel.getState().getRepeatedPassword());

        // assert that the values are as expected.
        assertEquals("yz", new String(pwdField.getPassword()));
        assertEquals("yz", viewModel.getState().getRepeatedPassword());
        assertEquals("", viewModel.getState().getPassword());
    }

    @org.junit.Test
    public void testCredentialsInfo() {

        // create the UI; note, we don't make a real SignupInputBoundary,
        // since we don't need it for this test.
        SignUpInputBoundary sib = null;
        SignUpController controller = new SignUpController(sib);
        SignUpViewModel viewModel = new SignUpViewModel();
        JPanel signupView = new SignUpView(viewModel, controller);
        JFrame jf = new JFrame();
        jf.setContentPane(signupView);
        jf.pack();
        jf.setVisible(true);

        // get a reference to the first password field
        LabelTextPanel usernameInfoPanel = (LabelTextPanel) signupView.getComponent(4);
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
        System.out.println("view-model: " + viewModel.getState().getCredentialsJSON());

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
        System.out.println("view-model: " + viewModel.getState().getCredentialsJSON());

        // assert that the values are as expected.
        assertEquals("yz", new String(usernameField.getText()));
        assertEquals("yz", viewModel.getState().getCredentialsJSON());
        assertEquals("", viewModel.getState().getPassword());
    }

    @org.junit.Test
    public void testSwitchToLoginView() {

        // create the UI; note, we don't make a real SignupInputBoundary,
        // since we don't need it for this test.
        SignUpInputBoundary sib = mock(SignUpInputBoundary.class, Answers.CALLS_REAL_METHODS);

        SignUpController controller = spy(new SignUpController(sib));
        SignUpViewModel viewModel = new SignUpViewModel();
        JPanel signupView = new SignUpView(viewModel, controller);
        JFrame jf = new JFrame();
        jf.setContentPane(signupView);
        jf.pack();
        jf.setVisible(true);

        // get a reference to the first password field
        JPanel buttonPanel = (JPanel) signupView.getComponent(5);
        JButton switchToLoginButton = (JButton) buttonPanel.getComponent(1);

        switchToLoginButton.doClick();


        // pause execution for a second
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        verify(controller, times(1)).switchToLoginView();
    }

    @org.junit.Test
    public void testSignUp() {

        // create the UI; note, we don't make a real SignupInputBoundary,
        // since we don't need it for this test.
        SignUpInputBoundary sib = mock(SignUpInputBoundary.class, Answers.CALLS_REAL_METHODS);

        SignUpController controller = spy(new SignUpController(sib));

        SignUpViewModel viewModel = new SignUpViewModel();
        JPanel signupView = new SignUpView(viewModel, controller);
        JFrame jf = new JFrame();
        jf.setContentPane(signupView);
        jf.pack();
        jf.setVisible(true);

        // get a reference to the first password field
        LabelTextPanel usernameInfoPanel = (LabelTextPanel) signupView.getComponent(1);
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
        LabelTextPanel passwordInfoPanel = (LabelTextPanel) signupView.getComponent(2);
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
        LabelTextPanel repeatPasswordInfoPanel = (LabelTextPanel) signupView.getComponent(3);
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
        LabelTextPanel credentialPanel = (LabelTextPanel) signupView.getComponent(4);
        JTextField credentialField = (JTextField) credentialPanel.getComponent(1);

        // create and dispatch KeyEvents to the UI
        KeyEvent credentialEvent = new KeyEvent(
                credentialField, // we are interacting with the pwdField
                KeyEvent.KEY_TYPED, //
                System.currentTimeMillis(), // say the event happened right now
                0, // no modifiers
                KeyEvent.VK_UNDEFINED, // for KEY_TYPED, the KeyCode is undefined per documentation
                'y'); // the character that is being typed

        credentialPanel.dispatchEvent(credentialEvent);


        // pause execution for a second
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // get a reference to the first password field
        JPanel buttonPanel = (JPanel) signupView.getComponent(5);
        JButton signUpButton = (JButton) buttonPanel.getComponent(0);

        signUpButton.doClick();


        // pause execution for a second
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        SignUpState currentState = viewModel.getState();
        verify(controller, times(1)).signUp(
                "y",
                "y",
                "y",
                "y"
        );

    }

    @org.junit.Test
    public void testSignUpFailed() {

        // create the UI; note, we don't make a real SignupInputBoundary,
        // since we don't need it for this test.
        SignUpInputBoundary sib = mock(SignUpInputBoundary.class, Answers.CALLS_REAL_METHODS);

        SignUpController controller = spy(new SignUpController(sib));

        SignUpViewModel viewModel = new SignUpViewModel();
        JPanel signupView = new SignUpView(viewModel, controller);
        JFrame jf = new JFrame();
        jf.setContentPane(signupView);
        jf.pack();
        jf.setVisible(true);

        // get a reference to the first password field
        LabelTextPanel usernameInfoPanel = (LabelTextPanel) signupView.getComponent(1);
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
        LabelTextPanel passwordInfoPanel = (LabelTextPanel) signupView.getComponent(2);
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
        LabelTextPanel repeatPasswordInfoPanel = (LabelTextPanel) signupView.getComponent(3);
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
        JPanel buttonPanel = (JPanel) signupView.getComponent(5);
        JButton signUpButton = (JButton) buttonPanel.getComponent(0);

        signUpButton.doClick();


        // pause execution for a second
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        SignUpState currentState = viewModel.getState();
        verify(controller, times(0)).signUp(
                "y",
                "y",
                "y",
                ""
        );

    }


    @org.junit.Test
    public void testSignUpUsernameError() throws IOException, ParseException {

        UserDataAccessInterface userDataAccessObject = new UserDataAccessObject("src/test/resources/users"+".json");

        SignUpViewModel viewModel = new SignUpViewModel();
        LogInViewModel loginviewModel = new LogInViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignUpOutputBoundary successPresenter = new SignUpPresenter(loginviewModel,viewModel,viewManagerModel);

        // create the UI; note, we don't make a real SignupInputBoundary,
        // since we don't need it for this test.
        SignUpInputBoundary interactor = new SignUpInteractor(userDataAccessObject, successPresenter);

        SignUpController controller = new SignUpController(interactor);


        JPanel signupView = new SignUpView(viewModel, controller);

        SignUpState currentState = viewModel.getState();
        currentState.setUsername(username);
        currentState.setPassword(password);
        currentState.setRepeatedPassword(repeatPassword);
        currentState.setCredentialsJSON(credential);

        // get a reference to the first password field
        JPanel buttonPanel = (JPanel) signupView.getComponent(5);
        JButton signUpButton = (JButton) buttonPanel.getComponent(0);

        signUpButton.doClick();

        // pause execution for a second
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        SignUpState signUpState = viewModel.getState();
        System.out.println("getUsername: " + signUpState.getUsername());
        System.out.println("getPassword: " + signUpState.getPassword());
        System.out.println("getCredentialsJSON: " + signUpState.getCredentialsJSON());
        assertNotNull(signUpState.getUsernameError());
        assertNull(signUpState.getPasswordError());
        assertNull(signUpState.getCredentialsError());

    }

    @org.junit.Test
    public void testSignUpPasswordError() throws IOException, ParseException {

        String timeNow = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new java.util.Date());
        UserDataAccessInterface userDataAccessObject = new UserDataAccessObject("src/test/resources/users"+timeNow+".json");

        SignUpViewModel viewModel = new SignUpViewModel();
        LogInViewModel loginviewModel = new LogInViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignUpOutputBoundary successPresenter = new SignUpPresenter(loginviewModel,viewModel,viewManagerModel);

        // create the UI; note, we don't make a real SignupInputBoundary,
        // since we don't need it for this test.
        SignUpInputBoundary interactor = new SignUpInteractor(userDataAccessObject, successPresenter);

        SignUpController controller = new SignUpController(interactor);


        JPanel signupView = new SignUpView(viewModel, controller);

        SignUpState currentState = viewModel.getState();
        currentState.setUsername(username);
        currentState.setPassword(password);
        currentState.setRepeatedPassword(repeatPassword+"y");
        currentState.setCredentialsJSON(credential);

        // get a reference to the first password field
        JPanel buttonPanel = (JPanel) signupView.getComponent(5);
        JButton signUpButton = (JButton) buttonPanel.getComponent(0);

        signUpButton.doClick();

        // pause execution for a second
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        SignUpState signUpState = viewModel.getState();
        System.out.println("getUsername: " + signUpState.getUsername());
        System.out.println("getPassword: " + signUpState.getPassword());
        System.out.println("getCredentialsJSON: " + signUpState.getCredentialsJSON());
        assertNull(signUpState.getUsernameError());
        assertNotNull(signUpState.getPasswordError());
        assertNull(signUpState.getCredentialsError());

    }

    @org.junit.Test
    public void testSignUpCredentialError() throws IOException, ParseException {

        String timeNow = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new java.util.Date());
        UserDataAccessInterface userDataAccessObject = new UserDataAccessObject("src/test/resources/users"+timeNow+".json");

        SignUpViewModel viewModel = new SignUpViewModel();
        LogInViewModel loginviewModel = new LogInViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignUpOutputBoundary successPresenter = new SignUpPresenter(loginviewModel,viewModel,viewManagerModel);

        // create the UI; note, we don't make a real SignupInputBoundary,
        // since we don't need it for this test.
        SignUpInputBoundary interactor = new SignUpInteractor(userDataAccessObject, successPresenter);

        SignUpController controller = new SignUpController(interactor);


        JPanel signupView = new SignUpView(viewModel, controller);

        SignUpState currentState = viewModel.getState();
        currentState.setUsername(username);
        currentState.setPassword(password);
        currentState.setRepeatedPassword(repeatPassword);
        currentState.setCredentialsJSON("fgfhfghfghfghfghf");

        // get a reference to the first password field
        JPanel buttonPanel = (JPanel) signupView.getComponent(5);
        JButton signUpButton = (JButton) buttonPanel.getComponent(0);

        signUpButton.doClick();

        // pause execution for a second
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        SignUpState signUpState = viewModel.getState();
        System.out.println("getUsername: " + signUpState.getUsername());
        System.out.println("getPassword: " + signUpState.getPassword());
        System.out.println("getCredentialsJSON: " + signUpState.getCredentialsJSON());
        assertNull(signUpState.getUsernameError());
        assertNull(signUpState.getPasswordError());
        assertNotNull(signUpState.getCredentialsError());

    }


}
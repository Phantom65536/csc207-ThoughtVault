package use_case.user;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogInOutInteractorTest extends TestCase {

    String username = "Elsie";
    String password = "123456";
    @Test
    public void logIn() {
        LoginInputData inputData = new LoginInputData(username, password);


    }

    @Test
    public void logOut() {
    }

    @Test
    public void switchToSignup() {
    }
}
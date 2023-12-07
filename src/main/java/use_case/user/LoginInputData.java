package use_case.user;

/**
 * Input Data for LogInInputBoundary
 */
public class LoginInputData {
    private final String userName;
    private final String password;

    /**
     * Instantiate a LogInInputData with the following attributes
     * @param userName
     * @param password
     */
    public LoginInputData(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * Get the username associated with this input data instance.
     * @return userName
     */
    public String getUsername() { return userName; }

    /**
     * Get the password associated with this input data instance.
     * @return password
     */
    public String getPassword() { return password; }
}

package interface_adapter.sign_up;

/**
 * State for SignUpViewModel
 */
public class SignUpState {
    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;
    private String repeatedPassword = "";
    private String credentialsJSON = "";
    private String credentialsError = null;

    /**
     * Instantiate a new SignUpState
     */
    public SignUpState() {}

    /**
     * Set the username of this state
     * @param username
     */
    public void setUsername(String username) { this.username = username; }

    /**
     * Set the password of this state
     * @param password
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * Set the repeated password of this state
     * @param repeatedPassword
     */
    public void setRepeatedPassword(String repeatedPassword) { this.repeatedPassword = repeatedPassword; }

    /**
     * Set the credentials JSON string of this state
     * @param credentialsJSON
     */
    public void setCredentialsJSON(String credentialsJSON) { this.credentialsJSON = credentialsJSON; }

    /**
     * Set the username error of this state
     * @param err
     */
    public void setUsernameError(String err) { this.usernameError = err; }

    /**
     * Set the password error of this state
     * @param err
     */
    public void setPasswordError(String err) { this.passwordError = err; }

    /**
     * Set the credentials string error of this state
     * @param err
     */
    public void setCredentialsError(String err) { this.credentialsError = err; }

    /**
     * Get the username stored in this state
     * @return username
     */
    public String getUsername() { return username; }

    /**
     * Get the password stored in this state
     * @return password
     */
    public String getPassword() { return password; }

    /**
     * Get the repeated password stored in this state
     * @return repeatedPassword
     */
    public String getRepeatedPassword() { return repeatedPassword; }

    /**
     * Get the credentials JSON string stored in this state
     * @return credentialsJSON
     */
    public String getCredentialsJSON() { return credentialsJSON; }

    /**
     * Get the username error stored in this state
     * @return username
     */
    public String getUsernameError() { return usernameError; }

    /**
     * Get the password error stored in this state
     * @return passwordError
     */
    public String getPasswordError() { return passwordError; }

    /**
     * Get the credentials string error stored in this state
     * @return credentialsError
     */
    public String getCredentialsError() { return credentialsError; }
}

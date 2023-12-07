package interface_adapter.log_in_out;

/**
 * State for LogInViewModel
 */
public class LogInState {
    private String username = "";
    private String password = "";
    private String usernameError = null;
    private String passwordError = null;

    /**
     * Instantiate LogInState
     */
    public LogInState() {}

    /**
     * Set the username of this state
     * @param usr
     */
    public void setUsername(String usr) { username = usr; }

    /**
     * Set the username error of this state
     * @param err
     */
    public void setUsernameError(String err) { usernameError = err; }

    /**
     * Set the password error of this state
     * @param err
     */
    public void setPasswordError(String err) { passwordError = err; }

    /**
     * Set the password of this state
     * @param pw
     */
    public void setPassword(String pw) { password = pw; }

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
     * Get the username error stored in this state
     * @return usernameError
     */
    public String getUsernameError() { return usernameError; }

    /**
     * Get the password error stored in this state
     * @return passwordError
     */
    public String getPasswordError() { return passwordError; }
}

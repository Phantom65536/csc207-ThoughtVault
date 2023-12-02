package interface_adapter.log_in_out;

public class LogInState {
    private String username = "";
    private String password = "";
    private String usernameError = null;
    private String passwordError = null;

    public LogInState() {}

    public void setUsername(String usr) { username = usr; }

    public void setUsernameError(String err) { usernameError = err; }
    public void setPasswordError(String err) { passwordError = err; }

    public void setPassword(String pw) { password = pw; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public String getUsernameError() { return usernameError; }
    public String getPasswordError() { return passwordError; }
}

package interface_adapter;

public class SignUpState {
    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;
    private String repeatedPassword = "";
    private String credentialsJSON = "";
    private String credentialsError = null;

    public SignUpState() {}

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setRepeatedPassword(String repeatedPassword) { this.repeatedPassword = repeatedPassword; }
    public void setCredentialsJSON(String credentialsJSON) { this.credentialsJSON = credentialsJSON; }
    public void setUsernameError(String err) { this.usernameError = err; }
    public void setPasswordError(String err) { this.passwordError = err; }
    public void setCredentialsError(String err) { this.credentialsError = err; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRepeatedPassword() { return repeatedPassword; }
    public String getCredentialsJSON() { return credentialsJSON; }
    public String getUsernameError() { return usernameError; }
    public String getPasswordError() { return passwordError; }
    public String getCredentialsError() { return credentialsError; }
}

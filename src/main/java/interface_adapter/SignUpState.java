package interface_adapter;

public class SignUpState {
    private String username = "";
    private String password = "";
    private String repeatedPassword = "";
    private String credentialsJSON = "";

    public SignUpState() {}

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setRepeatedPassword(String password) { this.repeatedPassword = password; }
    public void setCredentialsJSON(String credentialsJSON) { this.credentialsJSON = credentialsJSON; }
}

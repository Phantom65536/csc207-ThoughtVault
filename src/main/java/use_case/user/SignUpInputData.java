package use_case.user;

/**
 * Input Data class for signing up.
 */
public class SignUpInputData {
    private final String userName;
    private final String password;
    private final String repeatPassword;
    private final String APIKey;

    /**
     * Instantiate a SignUpInputData class with the following instance attributes
     * @param userName
     * @param password
     * @param repeatPassword
     * @param APIKey
     */
    public SignUpInputData(String userName, String password, String repeatPassword, String APIKey) {
        this.userName = userName;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.APIKey = APIKey;
    }

    /**
     * Retrieve the username stored
     * @return username
     */
    public String getUsername() { return userName; }

    /**
     * Retrieve the password stored
     * @return password
     */
    public String getPassword() { return password; }

    /**
     * Retrieve the repeated password stored
     * @return repeatPassword
     */
    public String getRepeatPassword() { return repeatPassword; }

    /**
     * Retrieve the Credentials JSON string stored
     * @return credentialsJsonString
     */
    public String getAPIKEy() { return APIKey; }
}

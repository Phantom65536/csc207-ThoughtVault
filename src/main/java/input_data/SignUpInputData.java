package input_data;

public class SignUpInputData {
    private final String userName;
    private final String password;
    private final String repeatPassword;
    private final String APIKey;

    public SignUpInputData(String userName, String password, String repeatPassword, String APIKey) {
        this.userName = userName;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.APIKey = APIKey;
    }

    public String getUsername() { return userName; }

    public String getPassword() { return password; }

    public String getRepeatPassword() { return repeatPassword; }

    public String getAPIKEy() { return APIKey; }
}

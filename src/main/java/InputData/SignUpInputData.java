package InputData;

public class SignUpInputData {
    private final String name;
    private final String userName;
    private final String password;
    private final String repeatPassword;
    private final String APIKey;

    public SignUpInputData(String name, String userName, String password, String repeatPassword, String APIKey) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.APIKey = APIKey;
    }

    public String getName() { return name; }

    public String getUsername() { return userName; }

    public String getPassword() { return password; }

    public String getRepeatPassword() { return repeatPassword; }

    public String getAPIKEy() { return APIKey; }
}

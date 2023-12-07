package use_case.user;

public class LoginInputData {
    private final String userName;
    private final String password;

    public LoginInputData(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUsername() { return userName; }

    public String getPassword() { return password; }
}

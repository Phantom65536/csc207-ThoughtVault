package interface_adapter;

public class LogInState {
    private String username = "";
    private String password = "";

    public LogInState() {}

    public void setUsername(String usr) { username = usr; }

    public void setPassword(String pw) { password = pw; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }
}

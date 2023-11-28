package OutputData;

public class UserOutputData {
    private final int userID;
    private final String name;
    private final String gcalID;

    public UserOutputData(int userID, String name, String gcalID) {
        this.userID = userID;
        this.name = name;
        this.gcalID = gcalID;
    }

    public int getUserID() { return userID; }

    public String getName() { return name; }

    public String getGcalID() { return gcalID; }
}

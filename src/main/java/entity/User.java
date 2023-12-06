package entity;

/**
 * This entity describes a single user in the system.
 * Its instance attributes include userid, username, password and credential JSON string.
 */
public class User implements UserInterface {
    private final int userid;
    private final String username;
    private final String password;
    private final String credential;

    /**
     * Create an instantce of User with the following instance attributes
     * @param userid
     * @param username
     * @param password
     * @param credential
     */
    public User(int userid, String username, String password, String credential) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.credential = credential;
    }

    /**
     * Obtain the userid of this User instance
     * @return userid
     */
    public int getUserid() {
        return userid;
    }

    /**
     * Obtain the username of this User instance
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Obtain the hashed password of this User instance
     * @return hashedPassword
     */
    public String getHashedPassword() {
        return password;
    }

    /**
     * Obtain the credentials JSON string of this User instance
     * @return credentialsString
     */
    public String getCredential() {
        return credential;
    }
}

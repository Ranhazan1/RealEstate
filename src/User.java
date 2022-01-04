import javax.jws.soap.SOAPBinding;

public class User {
    private String username;
    private String password;
    private String phoneNumber;
    private boolean isRealEstate;

    public User(String username, String password, String phoneNumber, boolean isRealEstate) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isRealEstate = isRealEstate;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean getIsRealEstate() {
        return this.isRealEstate;
    }

    public void setRealEstate(boolean realEstate) {
        this.isRealEstate = realEstate;
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("username: ").append(this.username).append(" ").append(this.phoneNumber);
        if (this.isRealEstate) {
            output.append(" (real estate broker).");
        } else {
            output.append(" (not a real estate broker).");
        }
        return output.toString();
    }
}

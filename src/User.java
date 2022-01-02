import javax.jws.soap.SOAPBinding;

public class User {
    private String username;
    private String password;
    private String phoneNumber;
    private boolean isRealEstate;

    public User(String username,String password,String phoneNumber,boolean isRealEstate){
        this.username=username;
        this.password=password;
        this.phoneNumber=phoneNumber;
        this.isRealEstate=isRealEstate;
        }

    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }
    public boolean getIsRealEstate(){
        return this.isRealEstate;
    }
    public void setRealEstate(boolean realEstate){
        this.isRealEstate=realEstate;
    }
    public String toString(){
        return "username: "+this.username+
                "\npassword: "+this.password+
                "\nphone number: "+this.phoneNumber+
                "\nis real estate: "+this.isRealEstate;
    }
}

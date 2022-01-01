import java.util.Scanner;

public class RealEstate {
    private User[] users;
    private Property[] properties;
    private Address[] addresses;

    public RealEstate(){
        this.users=new User[0];
        this.properties=new Property[0];
        this.addresses=new Address[10];
        this.addresses[0]=new Address("tel aviv","herzel street");
        this.addresses[1]=new Address("tel aviv","arlozorov street");
        this.addresses[2]=new Address("tel aviv","dizingof street");
        this.addresses[3]=new Address("tel aviv","ben gurion street");
        this.addresses[4]=new Address("jerusalem","king david street");
        this.addresses[5]=new Address("jerusalem","ben gurion street");
        this.addresses[6]=new Address("jerusalem","hillel street");
        this.addresses[7]=new Address("jerusalem","bar ilan street");
        this.addresses[8]=new Address("ashkelon","itzhak ben tzvi street");
        this.addresses[9]=new Address("ashkelon","herzrel street");
    }

    public void createUser(){
        Scanner scanner=new Scanner(System.in);
        String username=null;
        do {
            System.out.println("enter a username");
            username= scanner.nextLine();
            if (isUsernameExist(username)){
                System.out.println("this username is taken");
            }
        }while (isUsernameExist(username));
        String password=null;
        do {
            System.out.println("enter a password");
            password= scanner.nextLine();
            if (!isStrongPassword(password)){
                System.out.println("password is not strong enough try again");
            }
        }while (!isStrongPassword(password));
        String phoneNumber=null;
        do {
            System.out.println("enter your phoneNumber");
            phoneNumber= scanner.nextLine();
            if (!isPhoneNumberOk(phoneNumber)){
                System.out.println("the number is illegal");
            }
        }while (!isPhoneNumberOk(phoneNumber));
        boolean isRealEstate;
        int choose;
        do {
            System.out.println("1.i am a RealEstate");
            System.out.println("2.i am not a RealEstate");
            choose= scanner.nextInt();
            if (choose==1){
                isRealEstate=true;
            }else {isRealEstate=false;}
        }while (choose!=1&&choose!=2);
        addUserToArray(username,password,phoneNumber,isRealEstate);

    }
    private void addUserToArray(String username,String password,String phoneNumber,boolean isRealEstate){
        User[] newArray= new User[this.users.length+1];
        for (int i=0;i<this.users.length;i++){
            newArray[i]=this.users[i];
        }User userToAdd=new User(username,password,phoneNumber,isRealEstate);
        newArray[this.users.length]=userToAdd;
        this.users=newArray;
    }

    private boolean isPhoneNumberOk(String phoneNumber){
        boolean length=true;
        boolean start=true;
        boolean numberIsOk=false;
        for (int i=0;i<phoneNumber.length();i++){
            if (phoneNumber.length()!=10){
                length=false;
                break;
            }
            if (phoneNumber.charAt(0)!='0'&&phoneNumber.charAt(1)!='5'){
                start=false;
                break;
            }
        }
        if (length&&start){
            numberIsOk=true;
        }return numberIsOk;
    }
    private boolean isStrongPassword(String password) {
        boolean hasDigit = false;
        boolean hasSpecial = false;
        boolean isStrong = false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                hasDigit = true;
            }
            if (password.charAt(i) == '$' || password.charAt(i) == '%' || password.charAt(i) == '_') {
                hasSpecial = true;
            }
            if (hasDigit && hasSpecial) {
                isStrong=true;
                break;
            }
        }return isStrong;
    }

    private boolean isUsernameExist(String username){
        boolean exist=false;
        if (this.users.length>0) {
            for (int i = 0; i < this.users.length; i++) {
                User currentUser = this.users[i];
                if (currentUser.getUsername().equals(username)) {
                    exist = true;
                    break;
                }
            }
        }else{
            exist=false;
        }
        return exist;
    }
    public User login() {
        User user = new User(null,null,null,false);
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter your username:");
        String username = scanner.nextLine();
        System.out.println("enter your password:");
        String password= scanner.nextLine();
        for (int i = 0; i < users.length; i++) {
            String checkUsername = users[i].getUsername();
            String checkPassword = users[i].getPassword();
            if (username.equals(checkUsername) && password.equals(checkPassword)) {
                user = users[i];
                return user;
            }
        }return null;
    }
    public boolean postNewProperty(User user){
        Scanner scanner=new Scanner(System.in);
        boolean isUserCanPublish=false;
        int userPropertiesAmount=3;
        if (user.getIsRealEstate()){
            userPropertiesAmount=10;
        }if (properties.length<=userPropertiesAmount){
            isUserCanPublish=true;
        }if (isUserCanPublish){
            System.out.println("choose city ");
            String userCity= scanner.nextLine();
            String[] citiesToPrint=new String[10];
            for (int i=0;i<addresses.length;i++){
                citiesToPrint[i]=addresses[i].getCity();
            }
            for (int i=0;i<citiesToPrint.length;i++){
                String city=citiesToPrint[i];
                for (int j=0;j<citiesToPrint.length;j++){
                    if (i==j){
                        break;
                    }else {
                        if (city.equals(citiesToPrint[j])){
                            citiesToPrint[i]=null;
                        }
                    }
                }
            }for (int i=0;i<citiesToPrint.length;i++){
                if (citiesToPrint[i]!=null){
                    System.out.println(citiesToPrint[i]);
                }
            }for (int i=0;i<citiesToPrint.length;i++){
                if (userCity.equals(citiesToPrint[i])){

                }
            }
        }return isUserCanPublish;
    }
}

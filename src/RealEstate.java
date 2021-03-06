import javax.jws.soap.SOAPBinding;
import java.util.Scanner;

public class RealEstate {
    private User[] users;
    private Property[] properties;
    private Address[] addresses;

    public RealEstate() {
        this.users = new User[0];
        this.properties = new Property[0];
        this.addresses = new Address[10];
        this.addresses[0] = new Address("tel aviv", "herzel");
        this.addresses[1] = new Address("tel aviv", "arlozorov");
        this.addresses[2] = new Address("tel aviv", "dizingof");
        this.addresses[3] = new Address("tel aviv", "ben gurion");
        this.addresses[4] = new Address("jerusalem", "king david");
        this.addresses[5] = new Address("jerusalem", "ben gurion");
        this.addresses[6] = new Address("jerusalem", "hillel");
        this.addresses[7] = new Address("jerusalem", "bar ilan");
        this.addresses[8] = new Address("ashkelon", "itzhak ben tzvi");
        this.addresses[9] = new Address("ashkelon", "herzel");
    }

    public void createUser() {
        Scanner scanner = new Scanner(System.in);
        String username = null;
        do {
            System.out.println("enter a username");
            username = scanner.nextLine();
            if (isUsernameExist(username)) {
                System.out.println("this username is taken");
            }
        } while (isUsernameExist(username));
        String password = null;
        do {
            System.out.println("enter a password");
            password = scanner.nextLine();
            if (!isStrongPassword(password)) {
                System.out.println("password is not strong enough try again");
            }
        } while (!isStrongPassword(password));
        String phoneNumber = null;
        do {
            System.out.println("enter your phoneNumber");
            phoneNumber = scanner.nextLine();
            if (!isPhoneNumberOk(phoneNumber)) {
                System.out.println("the number is illegal");
            }
        } while (!isPhoneNumberOk(phoneNumber));
        boolean isRealEstate;
        int choose;
        do {
            System.out.println("1.i am a RealEstate");
            System.out.println("2.i am not a RealEstate");
            choose = scanner.nextInt();
            if (choose == 1) {
                isRealEstate = true;
            } else {
                isRealEstate = false;
            }
        } while (choose != 1 && choose != 2);
        addUserToArray(username, password, phoneNumber, isRealEstate);

    }

    private void addUserToArray(String username, String password, String phoneNumber, boolean isRealEstate) {
        User[] newArray = new User[this.users.length + 1];
        for (int i = 0; i < this.users.length; i++) {
            newArray[i] = this.users[i];
        }
        User userToAdd = new User(username, password, phoneNumber, isRealEstate);
        newArray[this.users.length] = userToAdd;
        this.users = newArray;
    }

    private boolean isPhoneNumberOk(String phoneNumber) {
        boolean length = true;
        boolean start = true;
        boolean numberIsOk = false;
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (phoneNumber.length() != 10) {
                length = false;
                break;
            }
            if (phoneNumber.charAt(0) != '0' && phoneNumber.charAt(1) != '5') {
                start = false;
                break;
            }
        }
        if (length && start) {
            numberIsOk = true;
        }
        return numberIsOk;
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
                isStrong = true;
                break;
            }
        }
        return isStrong;
    }

    private boolean isUsernameExist(String username) {
        boolean exist = false;
        if (this.users.length > 0) {
            for (int i = 0; i < this.users.length; i++) {
                User currentUser = this.users[i];
                if (currentUser.getUsername().equals(username)) {
                    exist = true;
                    break;
                }
            }
        } else {
            exist = false;
        }
        return exist;
    }

    public User login() {
        User user = new User(null, null, null, false);
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter your username:");
        String username = scanner.nextLine();
        System.out.println("enter your password:");
        String password = scanner.nextLine();
        for (int i = 0; i < users.length; i++) {
            String checkUsername = users[i].getUsername();
            String checkPassword = users[i].getPassword();
            if (username.equals(checkUsername) && password.equals(checkPassword)) {
                user = users[i];
                return user;
            }
        }
        return null;
    }

    public boolean postNewProperty(User user) {
        boolean succeed = false;
        boolean isUserCanPublish = isUserCanPublish(user);
        boolean chooseCity = false;
        boolean chooseStreet = false;
        if (isUserCanPublish) {
            String userCity = printCities(addresses);
            if (userCity != null) {
                chooseCity = true;
            }
            String userStreet = printStreets(userCity, addresses);
            if (userStreet != null) {
                chooseStreet = true;
            }
            if (chooseCity && chooseStreet) {
                Address address = new Address(userCity, userStreet);
                int type = propertyType();
                int floorNumber = -1;
                if (type == 1) {
                    floorNumber = floorNumber();
                }
                int roomNumber = roomNumber();
                int propertyNumber = propertyNumber();
                boolean forRent = forRent();
                double price = propertyPrice();
                addPropertyToArray(address, roomNumber, price, type, forRent, propertyNumber, floorNumber, user);
                succeed = true;
            }
        } else {
            System.out.println("you cant publish another property");
        }
        return succeed;
    }

    public boolean isUserCanPublish(User user) {
        boolean isUserCanPublish = false;
        int userPropertiesAmount = 3;
        if (user.getIsRealEstate()) {
            userPropertiesAmount = 10;
        }
        int userPublished = 0;
        for (int i = 0; i < this.properties.length; i++) {
            if (user.equals(properties[i].getUser())) {
                userPublished++;
            }
        }
        if (userPublished < userPropertiesAmount) {
            isUserCanPublish = true;
        }
        return isUserCanPublish;
    }

    public String printCities(Address[] addresses) {
        Scanner scanner = new Scanner(System.in);
        boolean cityExist = false;
        System.out.println("choose city: ");
        String[] citiesToPrint = new String[this.addresses.length];
        for (int i = 0; i < addresses.length; i++) {
            citiesToPrint[i] = addresses[i].getCity();
        }
        for (int i = 0; i < citiesToPrint.length; i++) {
            String city = citiesToPrint[i];
            for (int j = 0; j < citiesToPrint.length; j++) {
                if (i == j) {
                    break;
                } else {
                    if (city.equals(citiesToPrint[j])) {
                        citiesToPrint[i] = null;
                    }
                }
            }
        }
        for (int i = 0; i < citiesToPrint.length; i++) {
            if (citiesToPrint[i] != null) {
                System.out.println(citiesToPrint[i]);
            }
        }
        String userCity = scanner.nextLine();
        for (int i = 0; i < citiesToPrint.length; i++) {
            if (userCity.equals(citiesToPrint[i])) {
                cityExist = true;
                break;
            }
        }
        if (!cityExist) {
            System.out.println("the city " + userCity + " dont exist in the system");
            userCity = null;
        }
        return userCity;
    }

    public String printStreets(String city, Address[] addresses) {
        Scanner scanner = new Scanner(System.in);
        boolean streetExists = false;
        String userStreet=null;
        String[] newStreetsArray = new String[addresses.length];
        if (city!=null) {
            System.out.println("choose street: ");
            for (int i = 0; i < newStreetsArray.length; i++) {
                if (city.equals(addresses[i].getCity())) {
                    newStreetsArray[i] = addresses[i].getStreet();
                }
            }
            for (int i = 0; i < newStreetsArray.length; i++) {
                if (newStreetsArray[i] != null) {
                    System.out.println(newStreetsArray[i]);
                }
            }
            userStreet = scanner.nextLine();
            for (int i = 0; i < newStreetsArray.length; i++) {
                if (userStreet.equals(newStreetsArray[i])) {
                    streetExists = true;
                    break;
                }
            }
            if (!streetExists) {
                System.out.println("the street " + userStreet + " dont exist in the system");
                userStreet = null;
            }
        }

        return userStreet;
    }

    public int propertyType() {
        Scanner scanner = new Scanner(System.in);
        int typeNUmber = 0;
        while (typeNUmber > 3 || typeNUmber < 1) {
            System.out.println("pick the type off the property" +
                    "\n 1-regular apartment in building" +
                    "\n 2-penthouse" +
                    "\n 3- private house");
            typeNUmber = scanner.nextInt();
        }
        return typeNUmber;
    }

    public int roomNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter the number off rooms: ");
        int roomNumber = scanner.nextInt();
        return roomNumber;
    }

    public int floorNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter the floor number: ");
        int floorNumber = scanner.nextInt();
        return floorNumber;
    }

    public int propertyNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter the property number: ");
        int propertyNumber = scanner.nextInt();
        return propertyNumber;
    }

    public boolean forRent() {
        boolean forRent = false;
        Scanner scanner = new Scanner(System.in);
        int number = 0;
        while (number > 2 || number < 1) {
            System.out.println("do you want to rent orr sell?" +
                    "\n1-rent \n2-sell");
            number = scanner.nextInt();
            if (number == 1) {
                forRent = true;
            }
        }
        return forRent;
    }

    public double propertyPrice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter the price off the property :");
        double price = scanner.nextDouble();
        return price;
    }

    public void addPropertyToArray(Address address, int roomNumber, double price, int type, boolean forRent, int houseNumber
            , int floorNumber, User user) {
        Property[] newPropertyArray = new Property[this.properties.length + 1];
        for (int i = 0; i < properties.length; i++) {
            newPropertyArray[i] = this.properties[i];
        }
        Property propertyToAdd = new Property(address, roomNumber, price, type, forRent, houseNumber, floorNumber, user);
        newPropertyArray[this.properties.length] = propertyToAdd;
        this.properties = newPropertyArray;
    }

    public void removeProperty(User user) {
        Scanner scanner = new Scanner(System.in);
        boolean removeProperty = false;
        int arrayLength = 0;
        for (int i = 0; i < this.properties.length; i++) {
            if (user.equals(properties[i].getUser())) {
                removeProperty = true;
                arrayLength++;
            }
        }
        if (removeProperty) {
            int arrayIndex = 0;
            Property[] userProperties = new Property[arrayLength];
            for (int i = 0; i < this.properties.length; i++) {
                if (user.equals(properties[i].getUser())) {
                    userProperties[arrayIndex] = properties[i];
                    arrayIndex++;
                }
            }
            int propertyToRemove = 0;
            while (propertyToRemove < 1 || propertyToRemove > userProperties.length) {
                System.out.println("choose the property number to remove ");
                for (int i = 0; i < userProperties.length; i++) {
                    System.out.println(i + 1 + ":\n" + userProperties[i]);
                }
                propertyToRemove = scanner.nextInt();
            }
            userProperties[propertyToRemove - 1] = null;
            Property[] arrayAfterRemove = new Property[userProperties.length - 1];
            for (int i = 0; i < arrayAfterRemove.length; i++) {
                if (userProperties[i] == null) {
                    arrayAfterRemove[i] = userProperties[userProperties.length - 1];
                } else {
                    arrayAfterRemove[i] = userProperties[i];
                }
            }
            Property[] finalArray = new Property[this.properties.length - 1];
            int finalArrayIndex = 0;
            for (int i = 0; i < arrayAfterRemove.length; i++) {
                finalArray[i] = arrayAfterRemove[i];
                finalArrayIndex++;
            }
            for (int i = 0; i < finalArray.length; i++) {
                if (user != this.properties[i].getUser())
                    finalArray[finalArrayIndex] = this.properties[i];
                finalArrayIndex++;
            }
            this.properties = finalArray;

        } else {
            System.out.println("this user dont have any property to remove");
        }
    }

    public void printAllProperties() {
        for (int i = 0; i < this.properties.length; i++) {
            System.out.println(i + 1 + ": \n" + this.properties[i]);
        }
    }

    public void printUserProperties(User user) {
        boolean userHaveProperties = false;
        int newArrayLength = 0;
        for (int i = 0; i < properties.length; i++) {
            if (user.equals(properties[i].getUser())) {
                userHaveProperties = true;
                newArrayLength++;
            }
        }
        if (userHaveProperties) {
            int userPropertiesIndex = 0;
            Property[] userProperties = new Property[newArrayLength];
            for (int i = 0; i < this.properties.length; i++) {
                if (user.equals(properties[i].getUser())) {
                    userProperties[userPropertiesIndex] = properties[i];
                    userPropertiesIndex++;
                }
            }
            for (int i = 0; i < userProperties.length; i++) {
                System.out.println(i + 1 + ": \n" + userProperties[i]);
            }
        }
    }

    public Property[] search() {
        Property[] newArray = new Property[this.properties.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = this.properties[i];
        }
        int rentOrSale = searchRent();
        if (rentOrSale == 1) {
            for (int i = 0; i < newArray.length; i++) {
                if (newArray[i].isForRent()) {
                    newArray[i] = null;
                }
            }
        } else if (rentOrSale == 2) {
            for (int i = 0; i < newArray.length; i++) {
                if (!newArray[i].isForRent()) {
                    newArray[i] = null;
                }
            }
        }
        int type = searchType();
        if (type == 1) {
            for (int i = 0; i < newArray.length; i++) {
                if (newArray[i]!=null) {
                    if ((newArray[i].getType() == 2 || newArray[i].getType() == 3) && newArray[i] != null) {
                        newArray[i] = null;
                    }
                }
            }
        }
        if (type == 2) {
            for (int i = 0; i < newArray.length; i++) {
                if (newArray[i]!=null) {
                if ((newArray[i].getType() == 1 || newArray[i].getType() == 3)&&newArray[i]!=null) {
                    newArray[i] = null;
                }
                }
            }
        }
        if (type == 3) {
            for (int i = 0; i < newArray.length; i++) {
                if (newArray[i]!=null) {
                    if (newArray[i].getType() == 2 || newArray[i].getType() == 1) {
                        newArray[i] = null;
                    }
                }
            }
        }
        int numberOffRooms = searchRooms();
        if (numberOffRooms != -999) {
            for (int i = 0; i < newArray.length; i++) {
                if (newArray[i]!=null) {
                    if (newArray[i].getRoomNumber() != numberOffRooms) {
                        newArray[i] = null;
                    }
                }
            }
        }
        int minimumPrice = minimumPrice();
        int maximumPrice = maximumPrice();
        if (minimumPrice != -999) {
            for (int i = 0; i < newArray.length; i++) {
                if (newArray[i]!=null) {
                    if (newArray[i].getPrice() < minimumPrice) {
                        newArray[i] = null;
                    }
                }
            }
        }
        if (maximumPrice != -999) {
            for (int i = 0; i < newArray.length; i++) {
                if (newArray[i]!=null) {
                    if (newArray[i].getPrice() > maximumPrice) {
                        newArray[i] = null;
                    }
                }
            }
        }
        int arrayLength = 0;
        for (int i = 0; i < newArray.length; i++) {
            if (newArray[i] != null) {
                arrayLength++;
            }
        }
        Property[] finalArray = new Property[arrayLength];
        int index = 0;
        for (int i = 0; i < newArray.length; i++) {
            if (newArray[i]!=(null) ) {
                finalArray[index] = newArray[i];
                index++;
            }
        }
//        for (int i = 0; i < finalArray.length; i++) {
//            System.out.println(finalArray[i]);
//        }

        return finalArray;
    }

    public int minimumPrice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("what is the minimum price for property you want to search ? (-999 for all)");
        int choose = scanner.nextInt();
        return choose;
    }

    public int maximumPrice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("what is the maximum price for property you want to search ? (-999 for all)");
        int choose = scanner.nextInt();
        return choose;
    }

    public int searchRooms() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("how many rooms do want? (-999 for all)");
        int choose = scanner.nextInt();
        return choose;
    }


    public int searchRent() {
        Scanner scanner = new Scanner(System.in);
        int choose = 0;
        do {
            System.out.println("search properties for rent or for sale? (-999 for both) \n 1-for rent\n 2-for sale");
            choose = scanner.nextInt();
        } while (choose != 1 && choose != 2 && choose != -999);
        return choose;
    }

    public int searchType() {
        Scanner scanner = new Scanner(System.in);
        int choose = 0;
        do {
            System.out.println("what is the type off the property tou search for? (-999 for all types) \n 1-for apartment in building" +
                    "\n 2-penthouse \n 3-private house");
            choose = scanner.nextInt();
        } while (choose != 1 && choose != 2 && choose != 3 && choose != -999);
        return choose;
    }
}


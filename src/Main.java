import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RealEstate r1 = new RealEstate();
        int number;
        do {
            number = printMenu1();
            Scanner scanner = new Scanner(System.in);
            if (number == 1) {
                r1.createUser();
            } else if (number == 2) {
                User user = new User(null,null,null,false);
                user = r1.login();
               do {
                   if (user != null) {
                       number=printMenu2();
                       switch (number){
                           case 1:
                               r1.postNewProperty(user);
                               break;
                           case 2:
                               r1.removeProperty(user);
                               break;
                           case 3:
                               r1.printAllProperties();
                               break;
                           case 4:
                               r1.printUserProperties(user);
                               break;
                           case 5:
                               r1.search();
                               break;
                           case 6:
                               break;
                       }
                   }
               }while (number!=6);
            } else {
                break;
            }
        } while (number != 3);
        System.out.println("Goodbye");
    }

    public static int printMenu1() {
        int choose1 = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("1. create account");
            System.out.println("2. login to existing account");
            System.out.println("3.finish");
            choose1 = scanner.nextInt();
        } while (choose1 < 1 || choose1 > 3);
        return choose1;
    }

    public static int printMenu2() {
        int choose2 = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("1. publish a new property");
            System.out.println("2. remove property");
            System.out.println("3.display all properties in system");
            System.out.println("4.display properties posted by you");
            System.out.println("5.Search property by parameters");
            System.out.println("6.log out and back to menu");
            choose2 = scanner.nextInt();
        } while (choose2 < 1 || choose2 > 6);
        return choose2;
    }
}

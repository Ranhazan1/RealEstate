import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        RealEstate r1=new RealEstate();
        int number=1;
        if (number==1){
            r1.createUser();
        }

    }
    public static int printMenu(){
        int choose =0;
        Scanner scanner=new Scanner(System.in);
        do {
            System.out.println("1. create account");
            System.out.println("2. login to existing account");
            System.out.println("3.finish");
            choose= scanner.nextInt();
        }while (choose<1||choose>3);
        return choose;
    }
}

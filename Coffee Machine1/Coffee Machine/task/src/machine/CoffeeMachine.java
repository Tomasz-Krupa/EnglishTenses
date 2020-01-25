package machine;
import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final int WATER_FOR_ONE_CUP = 200;
        final int MILK_FOR_ONE_CUP = 50;
        final int BEANS_FOR_ONE_CUP = 15;

        System.out.println("Write how many ml of water the coffee machine has: ");
        int water  = scanner.nextInt();

        System.out.println("Write how many ml of milk the coffee machine has: ");
        int milk  = scanner.nextInt();

        System.out.println("Write how many grams of coffee beans the coffee machine has: ");
        int beans  = scanner.nextInt();

        System.out.println("Write how many cups of coffee you will need: ");
        int cups  = scanner.nextInt();

        int maximumCups = Math.min(Math.min(water / WATER_FOR_ONE_CUP, milk / MILK_FOR_ONE_CUP), beans / BEANS_FOR_ONE_CUP);

        if (maximumCups == cups) {
            System.out.println("Yes, I can make that amount of coffee");
        } else if (maximumCups > cups) {
            System.out.println("Yes, I can make that amount of coffee (and even " +
                    (maximumCups - cups) + " more than that)");
        } else {
            System.out.println("No, I can make only " + maximumCups + " cup(s) of coffee");
        }



    }
}
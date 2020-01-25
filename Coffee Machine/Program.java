package machine;

import java.io.Console;
import java.util.Scanner;


public class Program {
    public static void main(String[] args) {

        CoffeeMachine coffeeMachine = new CoffeeMachine();

        try (Scanner scanner = new Scanner(System.in)) {

            while (true) {

                System.out.println(coffeeMachine.getPrompt());

                if (coffeeMachine.makeAction(scanner.next()))
                    break;

                if (coffeeMachine.isMessageRequested())
                    System.out.println(coffeeMachine.getMessage());
            }
        }
    }
}

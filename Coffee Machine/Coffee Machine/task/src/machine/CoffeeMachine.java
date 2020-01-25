package machine;

import machine.drinks.Drink;
import machine.drinks.DrinkMenu;

import java.util.Scanner;

import static machine.IngredientsState.Ingredients;

/**
 * Klasa symulujaca prace automatu do sprzedazy kawy
 */
public class CoffeeMachine {
    static class Prompts
    {
        public final static String DEFAULT = "Write action (buy, fill, take, remaining, exit):";
        public final static String DEFAULT_UNKNOWN = "You can only choose buy, fill, take, remaining, exit. Try again: ";
        public final static String BUY = "What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ";
        public final static String BUY_OK = "I have enough resources, making you a coffee!";
        public final static String BUY_INSUFFICIENT_RESOURCES = "Sorry, not enough " + resources.getLackingResource() + "!";
        public final static String BUY_UNKNOWN = "You can only choose 1, 2, 3, back. Try again: ";
        public final static String TAKE_ALL = "I gave you $";

        public final static String FILL_WATER = "Write how many ml of water do you want to add:";
        public final static String FILL_MILK = "Write how many ml of milk do you want to add:";
        public final static String FILL_COFFEE = "Write how many grams of coffee beans do you want to add:";
        public final static String FILL_CUPS = "Write how many many disposable cups of coffees do you want to add:";
        public final static String INVALID_VALUE = "Enter a number between 0 and 1000000";
    }
    static class InternalStates
    {
        public final static int DEFAULT = 0;
        public final static int BUY = 10;
        public final static int FILL_BEGIN = 20;
        public final static int FILL_WATER = 20;
        public final static int FILL_MILK = 21;
        public final static int FILL_COFFEE = 22;
        public final static int FILL_CUPS = 23;
        public final static int FILL_END = 23;
    }

    /**
     * Zasoby maszyny
     */
    private static IngredientsState resources = new IngredientsState();
    /**
     * Wewnetrzny stan, w ktorym znajduje sie maszyna
     */
    private int internalState = InternalStates.DEFAULT;

    private boolean _isMessageRequested = false;

    /**
     * Czy wyswietlic dodatkowy komunikat dla uzytkownika
     * @return czy komunikat jest konieczny
     */
    public boolean isMessageRequested()
    {
        return _isMessageRequested;
    }
    private String message = "";

    /**
     * Zwraca dodatkowy komunikat przeznaczony dla uzytkownika
     * @return komunikat dla uzytkownika
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * Zwraca zachete do pokazania uzytkownikowi
     * @return napis do wyswietlenia uzytkownikowi
     */
    public String getPrompt()
    {
        switch (internalState) {
            case InternalStates.BUY:
                return Prompts.BUY;
            case InternalStates.FILL_WATER:
                return Prompts.FILL_WATER;
            case InternalStates.FILL_MILK:
                return Prompts.FILL_MILK;
            case InternalStates.FILL_COFFEE:
                return Prompts.FILL_COFFEE;
            case InternalStates.FILL_CUPS:
                return Prompts.FILL_CUPS;
            default:
                return Prompts.DEFAULT;
        }
    }

    /**
     * Wykonuje zadana przez uzytkownika akcje
     * @param action akcja do wykonaia
     * @return czy zakonczyc prace programu
     */
    public boolean makeAction(String action) {
        _isMessageRequested = false;
        switch (internalState) {
            case InternalStates.BUY:
                makeBuyAction(action);
                break;
            case InternalStates.FILL_WATER:
            case InternalStates.FILL_MILK:
            case InternalStates.FILL_COFFEE:
            case InternalStates.FILL_CUPS:
                makeFillAction(action);
                break;
            default:
            return makeMainAction(action);
        }
        return false;
    }
    private boolean makeMainAction(String action) {
        boolean exit = false;
        switch (action) {
            case "buy":
                internalState = InternalStates.BUY;
                break;
            case "fill":
                internalState = InternalStates.FILL_BEGIN;
                break;
            case "take":
                _isMessageRequested = true;
                message = Prompts.TAKE_ALL + resources.takeAllMoney();
                break;
            case "remaining":
                _isMessageRequested = true;
                message = resources.getResources();
                break;
            case "exit":
                exit = true;
                break;
            default:
                _isMessageRequested = true;
                message = Prompts.DEFAULT_UNKNOWN;
                break;
        }
        return exit;
    }
    private void makeBuyAction(String action)
    {
        Drink drink = null;
        switch (action) {
            case "1":
                drink = DrinkMenu.Espresso;
                break;
            case "2":
                drink = DrinkMenu.Latte;
                break;
            case "3":
                drink = DrinkMenu.Cappuccino;
                break;
            case "back":
                internalState = 0;
                break;
            default:
                _isMessageRequested = true;
                message = Prompts.BUY_UNKNOWN;
                break;
        }
        if (drink!=null) {
            internalState = InternalStates.DEFAULT;
            _isMessageRequested = true;
            if (resources.canMakeADrink(drink)) {
                resources.makeDrink(drink);
                message = Prompts.BUY_OK;
            } else {
                message = Prompts.BUY_INSUFFICIENT_RESOURCES + resources.getLackingResource() + "!";
            }
        }
    }
    private void makeFillAction(String action) {
        int value = 0;
        boolean parsed = false;
        try {
            value = Integer.parseInt(action);
            if (value<0 || value > 1000000)
            {throw new NumberFormatException();}
            parsed = true;
        } catch (NumberFormatException e) {
            _isMessageRequested = true;
            message = Prompts.INVALID_VALUE;
        }
        if (parsed) {
            switch (internalState) {
                case InternalStates.FILL_WATER:
                    resources.addResource(Ingredients.WATER, value);
                    break;
                case InternalStates.FILL_MILK:
                    resources.addResource(Ingredients.MILK, value);
                    break;
                case InternalStates.FILL_COFFEE:
                    resources.addResource(Ingredients.COFFEE, value);
                    break;
                case InternalStates.FILL_CUPS:
                    resources.addResource(Ingredients.CUPS, value);
                    break;
            }
            internalState++;
            if (internalState> InternalStates.FILL_END)
                internalState = InternalStates.DEFAULT;
        }
    }

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


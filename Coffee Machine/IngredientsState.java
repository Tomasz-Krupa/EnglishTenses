package machine;

import machine.drinks.*;

public class IngredientsState {

    public static class Ingredients
    {
        public static final int WATER = 1;
        public static final int MILK = 2;
        public static final int COFFEE = 3;
        public static final int CUPS = 4;
    }

    private int waterInside = 400;
    private int milkInside = 540;
    private int moneyInside = 550;
    private int coffeeInside = 120;
    private int cupsInside = 9;

    private String lackingResource = "";
    public void setLackingResource(String resource) {
        lackingResource = resource;
    }
    public String getLackingResource() {
        return lackingResource;
    }

    public boolean canMakeADrink(Drink drink)
    {
        if (cupsInside<1) {
            setLackingResource("cups");
            return false;
        }
        if (waterInside<drink.waterNeeded()) {
            setLackingResource("water");
            return false;
        }
        if (coffeeInside<drink.coffeeBeansNeeded()) {
            setLackingResource("coffee");
            return false;
        }
        if (milkInside<drink.milkNeeded()) {
            setLackingResource("milk");
            return false;
        }
        return true;
    }

    public void makeDrink(Drink drink) {
        if (!canMakeADrink(drink))
            throw new IllegalStateException("Not enough resources: " + getLackingResource());

        waterInside -= drink.waterNeeded();
        milkInside -= drink.milkNeeded();
        coffeeInside -= drink.coffeeBeansNeeded();
        cupsInside--;
        moneyInside += drink.price();
    }

    public String getResources() {
        return "The coffee machine has: \n"
                + waterInside + " of water \n" +
                milkInside + " of milk\n" +
                coffeeInside + " of coffee beans\n" +
                cupsInside + " of disposable cups\n" +
                moneyInside + " of money\n";
    }
    public void addResource(int type, int value) {
        switch (type) {
            case Ingredients.WATER:
                waterInside += value;
                break;
            case Ingredients.MILK:
                milkInside += value;
                break;
            case Ingredients.COFFEE:
                coffeeInside += value;
                break;
            case Ingredients.CUPS:
                cupsInside += value;
                break;
            default:
                break;
        }
    }
    public int takeAllMoney()
    {
        int money = moneyInside;
        moneyInside = 0;
        return money;
    }
}

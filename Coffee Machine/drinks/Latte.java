package machine.drinks;

// For the latte, the coffee machine needs 350 ml of water, 75 ml of milk, and 20 g of coffee beans. It costs $7.
public class Latte implements Drink {
    @Override
    public int milkNeeded() {
        return 75;
    }

    @Override
    public int waterNeeded() {
        return 350;
    }

    @Override
    public int coffeeBeansNeeded() {
        return 20;
    }

    @Override
    public int price() {
        return 7;
    }
}

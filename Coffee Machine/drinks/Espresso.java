package machine.drinks;

//    For the espresso, the coffee machine needs 250 ml of water and 16 g of coffee beans. It costs $4.
public class Espresso implements Drink {
    @Override
    public int milkNeeded() {
        return 0;
    }

    @Override
    public int waterNeeded() {
        return 250;
    }

    @Override
    public int coffeeBeansNeeded() {
        return 16;
    }

    @Override
    public int price() {
        return 4;
    }
}

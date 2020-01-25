package machine.drinks;



//  And for the cappuccino, the coffee machine needs 200 ml of water, 100 ml of milk, and 12 g of coffee. It costs $6.
public class Cappuccino implements Drink {
    @Override
    public int milkNeeded() {
        return 100;
    }

    @Override
    public int waterNeeded() {
        return 200;
    }

    @Override
    public int coffeeBeansNeeded() {
        return 12;
    }

    @Override
    public int price() {
        return 6;
    }
}

public class PizzaBaseFactory {

    public static Pizza createPizza(String PizzaName) {
        return switch (PizzaName) {
            case "Margherrita" -> new Margherrita();
            case "Farmhouse" -> new Farmhouse();
            default -> new Pepperoni();
        };
    }
}

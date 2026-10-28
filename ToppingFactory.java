public class ToppingFactory {

    public static Toppings getTopping(String topping){
        return switch (topping) {
            case "Cheese" -> new Cheese();
            case "Olive" -> new Olives();
            case "Sauce" -> new Sauce();
            default -> new Cheese();
        };
    }
}

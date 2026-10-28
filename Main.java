public class Main {

    static void main(String[] args) {

        PizzaShop shop = PizzaShop.getInstance();

        Order pizza = new Order.Builder()
                .base("Farmhouse")
                .cheeseRequired(true)
                .sauceRequired(true)
                .build();

        int price = pizza.Checkout();
        System.out.println("Pizza: " + price);
    }
}
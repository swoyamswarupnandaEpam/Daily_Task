package org.example;

public class OrderService {

    public void placeOrder(Pizza pizza) {

        if (pizza == null) {
            System.out.println("Invalid pizza order.");
            return;
        }

        ShopConfig config = ShopConfig.getInstance();

        double subtotal = pizza.calculatePrice();
        double tax = subtotal * config.getTaxRate();
        double total = subtotal + tax;

        System.out.println("\n========== ORDER SUMMARY ==========");
        System.out.println("Shop: " + config.getShopName());
        System.out.println("Pizza: " + pizza.getPizzaType());
        System.out.println("Size: " + pizza.getSize());
        System.out.println("Crust: " + pizza.getCrust());
        System.out.println("Extra Cheese: " + pizza.hasExtraCheese());
        System.out.println("Toppings: " + pizza.getToppings());

        System.out.printf("Subtotal: Rs. %.2f%n", subtotal);
        System.out.printf("Tax: Rs. %.2f%n", tax);
        System.out.printf("Total Price: Rs. %.2f%n", total);

        System.out.println("====================================");
    }
}

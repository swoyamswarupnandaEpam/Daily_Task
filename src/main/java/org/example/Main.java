package org.example;
public class Main {

    public static void main(String[] args) {
        ShopConfig config = ShopConfig.getInstance();

        System.out.println("Welcome to " + config.getShopName());

        Pizza basePizza = PizzaFactory.createPizza("Farmhouse");

        if (basePizza == null) {
            System.out.println("Pizza type not available.");
            return;
        }

        Pizza customizedPizza = new PizzaBuilder(basePizza)
                .setSize("Large")
                .setCrust("Thin")
                .addExtraCheese()
                .addTopping("Mushroom")
                .addTopping("Olives")
                .build();

        OrderService orderService = new OrderService();

        orderService.placeOrder(customizedPizza);
    }
}
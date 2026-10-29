package org.example;

import java.util.ArrayList;
import java.util.List;

public class Pizza {

    private String pizzaType;
    private double basePrice;
    private String size;
    private String crust;
    private boolean extraCheese;
    private List<String> toppings;

    public Pizza(String pizzaType, double basePrice, String size,
                 String crust, boolean extraCheese, List<String> toppings) {

        this.pizzaType = pizzaType;
        this.basePrice = basePrice;
        this.size = size;
        this.crust = crust;
        this.extraCheese = extraCheese;
        this.toppings = new ArrayList<>(toppings);
    }

    public String getPizzaType() {
        return pizzaType;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public String getSize() {
        return size;
    }

    public String getCrust() {
        return crust;
    }

    public boolean hasExtraCheese() {
        return extraCheese;
    }

    public List<String> getToppings() {
        return new ArrayList<>(toppings);
    }

    public double calculatePrice() {

        double price = basePrice;

        // Calculate price based on size
        if (size.equalsIgnoreCase("Small")) {
            price = price * 0.8;
        } else if (size.equalsIgnoreCase("Large")) {
            price = price * 1.3;
        }

        // Add crust price
        if (crust.equalsIgnoreCase("Stuffed")) {
            price = price + 80;
        }

        // Add extra cheese price
        if (extraCheese) {
            price = price + 40;
        }

        // Add topping prices
        for (String topping : toppings) {

            if (topping.equalsIgnoreCase("Mushroom")) {
                price = price + 30;
            } else if (topping.equalsIgnoreCase("Olives")) {
                price = price + 20;
            } else if (topping.equalsIgnoreCase("Jalapenos")) {
                price = price + 25;
            } else if (topping.equalsIgnoreCase("Onion")) {
                price = price + 15;
            } else if (topping.equalsIgnoreCase("Pepperoni")) {
                price = price + 40;
            }
        }

        return price;
    }
}
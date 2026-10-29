package org.example;
import java.util.ArrayList;

public class PizzaFactory {

    public static Pizza createPizza(String pizzaType) {

        if (pizzaType.equalsIgnoreCase("Margherita")) {

            return new Pizza(
                    "Margherita",
                    200,
                    "Medium",
                    "Classic",
                    false,
                    new ArrayList<>()
            );

        } else if (pizzaType.equalsIgnoreCase("Farmhouse")) {

            return new Pizza(
                    "Farmhouse",
                    300,
                    "Medium",
                    "Classic",
                    false,
                    new ArrayList<>()
            );

        } else if (pizzaType.equalsIgnoreCase("Pepperoni")) {

            return new Pizza(
                    "Pepperoni",
                    350,
                    "Medium",
                    "Classic",
                    false,
                    new ArrayList<>()
            );
        }

        return null;
    }
}
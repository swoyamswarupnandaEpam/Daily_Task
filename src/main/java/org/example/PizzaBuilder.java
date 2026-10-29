package org.example;
import java.util.ArrayList;
import java.util.List;

public class PizzaBuilder {

    private Pizza basePizza;

    private String size;
    private String crust;
    private boolean extraCheese;
    private List<String> toppings;

    public PizzaBuilder(Pizza basePizza) {

        this.basePizza = basePizza;

        this.size = basePizza.getSize();
        this.crust = basePizza.getCrust();
        this.extraCheese = basePizza.hasExtraCheese();
        this.toppings = basePizza.getToppings();
    }

    public PizzaBuilder setSize(String size) {
        this.size = size;
        return this;
    }

    public PizzaBuilder setCrust(String crust) {
        this.crust = crust;
        return this;
    }

    public PizzaBuilder addExtraCheese() {
        this.extraCheese = true;
        return this;
    }

    public PizzaBuilder addTopping(String topping) {
        toppings.add(topping);
        return this;
    }

    public Pizza build() {

        return new Pizza(
                basePizza.getPizzaType(),
                basePizza.getBasePrice(),
                size,
                crust,
                extraCheese,
                toppings
        );
    }
}

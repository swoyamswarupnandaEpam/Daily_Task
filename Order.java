import java.util.ArrayList;
import java.util.List;

public class Order{


    private Pizza PizzaName;
    private final List<String> toppings = new ArrayList<>();

    public Order(Builder builder) {
        this.PizzaName = builder.PizzaName;
        this.toppings.addAll(builder.toppings);
    }

    public int Checkout(){
        int bill = PizzaName.getPrice();
        for(String topping : toppings){
            Toppings addons = ToppingFactory.getTopping(topping);
            bill += addons.getPrice();
        }
        return bill;
    }



    static class Builder{
        private Pizza PizzaName;
        private final List<String> toppings = new ArrayList<>();

        public Builder base(String PizzaName) {
            this.PizzaName = PizzaBaseFactory.createPizza(PizzaName);
            return this;
        }

        public Builder cheeseRequired(boolean response) {
            toppings.add("cheese");
            return this;
        }

        public Builder olivesRequired(boolean response) {
            toppings.add("olives");
            return this;
        }

        public Builder sauceRequired(boolean response) {
            toppings.add("Sauce");
            return this;
        }

        public Order build(){
            return new Order(this);
        }
    }


}

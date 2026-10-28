
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ShopConfig {

    private static final ShopConfig instance = new ShopConfig();

    private final String shopName = "Pizza Corner";


    private final double taxRate = 0.05;

    private ShopConfig() {
    }



    public static ShopConfig getInstance() {
        return instance;
    }



    public String getShopName() {
        return shopName;
    }

    public double getTaxRate() {
        return taxRate;
    }


}

interface Pizza {
    String getName();
    double getBasePrice();
}


class MargheritaPizza implements Pizza {

    public String getName() {
        return "Margherita";
    }


    public double getBasePrice() {
        return 150;
    }
}

class FarmhousePizza implements Pizza {

    public String getName() {
        return "Farmhouse";
    }



    public double getBasePrice() {
        return 220;
    }
}

class PepperoniPizza implements Pizza {

    public String getName() {
        return "Pepperoni";
    }

    public double getBasePrice() {
        return 250;
    }
}



class PizzaFactory {

    public static Pizza createPizza(String type) {

        if ("margherita".equalsIgnoreCase(type)) {
            return new MargheritaPizza();
        } else if ("farmhouse".equalsIgnoreCase(type)) {
            return new FarmhousePizza();
        } else if ("pepperoni".equalsIgnoreCase(type)) {
            return new PepperoniPizza();
        }

        throw new IllegalArgumentException(
                "Invalid pizza type: " + type);
    }
}



class PizzaOrder {

    private final Pizza pizza;

    private final String size;
    private final String crust;
    private final boolean extraCheese;
    private final List<String> toppings;

    private PizzaOrder(Builder builder) {
        this.pizza = builder.pizza;
        this.size = builder.size;
        this.crust = builder.crust;
        this.extraCheese = builder.extraCheese;
        this.toppings = new ArrayList<>(builder.toppings);
    }

    public Pizza getPizza() {
        return pizza;
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

    static class Builder {

        private final Pizza pizza;
        private String size = "Medium";
        private String crust = "Regular";
        private boolean extraCheese = false;
        private List<String> toppings = new ArrayList<>();

        public Builder(Pizza pizza) {
            if (pizza == null) {
                throw new IllegalArgumentException(
                        "Pizza cannot be null");
            }
            
            this.pizza = pizza;
        }



        public Builder size(String size) {
            this.size = size;
            return this;
        }

        public Builder crust(String crust) {
            this.crust = crust;
            return this;
        }

        public Builder extraCheese(boolean extraCheese) {
            this.extraCheese = extraCheese;
            return this;
        }

        public Builder addTopping(String topping) {
            toppings.add(topping);
            return this;
        }

        public PizzaOrder build() {
            return new PizzaOrder(this);
        }
    }
}

interface PricingStrategy {
    double calculatePrice(PizzaOrder order);
}



class StandardPricingStrategy implements PricingStrategy {

    public double calculatePrice(PizzaOrder order) {

        double price = order.getPizza().getBasePrice();

        if (order.getSize().equalsIgnoreCase("Small")) {
            price *= 0.8;
        } else if (order.getSize().equalsIgnoreCase("Medium")) {
            price *= 1.0;
        } else if (order.getSize().equalsIgnoreCase("Large")) {
            price *= 1.3;
        }

        if (order.getCrust().equalsIgnoreCase("Thin")) {
            price += 10;
        } else if (order.getCrust().equalsIgnoreCase("Cheese Burst")) {
            price += 40;
        }

        if (order.hasExtraCheese()) {
            price += 20;
        }

        for (String topping : order.getToppings()) {
            if (topping.equalsIgnoreCase("Olives")) {
                price += 15;
            } else if (topping.equalsIgnoreCase("Mushrooms")) {
                price += 20;
            } else if (topping.equalsIgnoreCase("Jalapenos")) {
                price += 15;
            }
        }

        return price;
    }
}




class OrderValidator {

    private final List<String> sizes =
            Arrays.asList("small", "medium", "large");

    private final List<String> crusts =
            Arrays.asList("regular", "thin", "cheese burst");

    private final List<String> toppings =
            Arrays.asList("olives", "mushrooms", "jalapenos");

    public void validate(PizzaOrder order) {

        if (order == null || order.getPizza() == null) {
            throw new IllegalArgumentException(
                    "Order and pizza cannot be null");
        }

        if (order.getSize() == null
                || !sizes.contains(order.getSize())) {
            throw new IllegalArgumentException("invalid pizza size");
        }

        if (order.getCrust() == null
                || !crusts.contains(order.getCrust())) {
            throw new IllegalArgumentException("invalid crust type");
        }

        for (String topping : order.getToppings()) {
            if (topping == null || !toppings.contains(topping)) {
                throw new IllegalArgumentException(
                        "invalid topping: " + topping);
            }
        }
    }
}

class OrderService {

    private final PricingStrategy pricing;
    private final OrderValidator validator;

    public OrderService(
            PricingStrategy pricing,
            OrderValidator validator) {
        this.pricing = pricing;
        this.validator = validator;
    }

    public void placeOrder(PizzaOrder order) {

        validator.validate(order);

        double subtotal = pricing.calculatePrice(order);

        ShopConfig config = ShopConfig.getInstance();

        double tax = subtotal * config.getTaxRate();
        double total = subtotal + tax;

        
        System.out.println("Shop: " + config.getShopName());
        System.out.println("Pizza: " + order.getPizza().getName());
        System.out.println("Size: " + order.getSize());
        System.out.println("Crust: " + order.getCrust());
        System.out.println("extra cheese: " + order.hasExtraCheese());
        System.out.println("toppings: " + order.getToppings());

       System.out.printf("Subtotal: Rs. %.2f%n", subtotal);
        System.out.printf("Tax: Rs. %.2f%n", tax);
        System.out.printf("Total: Rs. %.2f%n", total);
    }
}

public class Main {

    public static void main(String[] args) {

        try {
            ShopConfig first = ShopConfig.getInstance();
            ShopConfig second = ShopConfig.getInstance();

            System.out.println(
                    "Same configuration instance: " + (first == second));

            Pizza pizza = PizzaFactory.createPizza("farmhouse");

            PizzaOrder order = new PizzaOrder.Builder(pizza)
                    .size("large")
                    .crust("cheese burst")
                    .extraCheese(true)
                    .addTopping("olives")
                    .addTopping("mushrooms")
                    .build();

            PricingStrategy pricing = new StandardPricingStrategy();
            OrderValidator validator = new OrderValidator();

            OrderService service = new OrderService(pricing, validator);
            service.placeOrder(order);

        } catch (IllegalArgumentException e) {
            System.out.println("Order rejected: " + e.getMessage());
        }
    }
}

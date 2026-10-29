// Problem Statement
//You are developing a Java application for a pizza shop where customers can place
// orders for different types of pizzas, customize their orders, and receive their final order details.
//The shop offers multiple pizza varieties, such as Margherita, Farmhouse, and Pepperoni.
// Each pizza can have different sizes, crust types, and additional toppings. The application
// must also maintain consistent shop configuration, such as the shop name and tax rate,
// throughout its execution.As the application grows, the team wants to avoid duplicated
// object creation logic, complex constructors, and inconsistent configuration.
//Your task: Design and implement a solution using appropriate object-oriented design
// principles to meet the following requirements:
//1. The shop configuration must remain consistent throughout the application,
// and repeated requests for the configuration should return the same instance.
//2. The application must create different pizza types dynamically based on the
// customer's selection, without requiring the ordering code to know the internal creation logic.
//3. Customers must be able to customize their pizzas by selecting size, crust,
// cheese, and multiple toppings. They should be able to specify only the options
// they need, without passing every possible parameter to a constructor.
//4. The application must validate customer orders, calculate the final price based
// on pizza type, size, and additional toppings, and reject invalid orders.
//5. Demonstrate the complete order flow, from selecting a pizza to generating a final
// order summary containing the pizza type, customizations, and total price.
//Additional challenge: Design the solution so that adding a new pizza variety
// or topping does not require major changes to the existing ordering logic.


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// Singleton Pattern
class ShopConfig {
    private static final ShopConfig instance = new ShopConfig();

    private final String shopName = "Pizza-Shop";
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

// Abstraction
abstract class Pizza {
    private final String name;
    private final double basePrice;

    protected Pizza(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }
}

// Inheritance
class Margherita extends Pizza {
    public Margherita() {
        super("Margherita", 150);
    }
}

class Farmhouse extends Pizza {
    public Farmhouse() {
        super("Farmhouse", 250);
    }
}

class Pepperoni extends Pizza {
    public Pepperoni() {
        super("Pepperoni", 300);
    }
}

// Factory Pattern
class PizzaFactory {
    public static Pizza createPizza(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Pizza type is required");
        }

        switch (type.trim().toLowerCase()) {
            case "margherita":
                return new Margherita();
            case "farmhouse":
                return new Farmhouse();
            case "pepperoni":
                return new Pepperoni();
            default:
                throw new IllegalArgumentException("Invalid pizza type");
        }
    }
}

// Customization options
enum PizzaSize {
    SMALL(0.75), MEDIUM(1.0), LARGE(1.5);

    private final double multiplier;

    PizzaSize(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }
}

enum CrustType {
    THIN, THICK, CHEESE_BURST
}

enum Topping {
    MUSHROOM(30), OLIVES(20), JALAPENO(25);

    private final double price;

    Topping(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

// Builder Pattern
class PizzaOrder {
    private final Pizza pizza;
    private final PizzaSize size;
    private final CrustType crust;
    private final boolean extraCheese;
    private final List<Topping> toppings;

    private PizzaOrder(Builder builder) {
        pizza = builder.pizza;
        size = builder.size;
        crust = builder.crust;
        extraCheese = builder.extraCheese;
        toppings = Collections.unmodifiableList(
                new ArrayList<>(builder.toppings)
        );
    }

    public Pizza getPizza() {
        return pizza;
    }

    public PizzaSize getSize() {
        return size;
    }

    public CrustType getCrust() {
        return crust;
    }

    public boolean hasExtraCheese() {
        return extraCheese;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    static class Builder {
        private final Pizza pizza;
        private PizzaSize size = PizzaSize.MEDIUM;
        private CrustType crust = CrustType.THIN;
        private boolean extraCheese;
        private final List<Topping> toppings = new ArrayList<>();

        public Builder(Pizza pizza) {
            this.pizza = pizza;
        }

        public Builder size(PizzaSize size) {
            this.size = size;
            return this;
        }

        public Builder crust(CrustType crust) {
            this.crust = crust;
            return this;
        }

        public Builder extraCheese(boolean value) {
            extraCheese = value;
            return this;
        }

        public Builder addTopping(Topping topping) {
            toppings.add(topping);
            return this;
        }

        public PizzaOrder build() {
            if (pizza == null || size == null || crust == null
                    || toppings.contains(null)) {
                throw new IllegalArgumentException("Invalid order");
            }

            return new PizzaOrder(this);
        }
    }
}

// Validation and pricing
class OrderService {
    private final ShopConfig config = ShopConfig.getInstance();

    public void printSummary(PizzaOrder order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        double subtotal =
                order.getPizza().getBasePrice()
                        * order.getSize().getMultiplier();

        for (Topping topping : order.getToppings()) {
            subtotal += topping.getPrice();
        }

        if (order.hasExtraCheese()) {
            subtotal += 35;
        }

        if (order.getCrust() == CrustType.CHEESE_BURST) {
            subtotal += 40;
        }

        double tax = subtotal * config.getTaxRate();
        double total = subtotal + tax;

        System.out.println("\nShop: " + config.getShopName());
        System.out.println("Pizza: " + order.getPizza().getName());
        System.out.println("Size: " + order.getSize());
        System.out.println("Crust: " + order.getCrust());
        System.out.println("Extra cheese: " + order.hasExtraCheese());
        System.out.println("Toppings: " + order.getToppings());
        System.out.printf("Subtotal: ₹%.2f%n", subtotal);
        System.out.printf("Tax: ₹%.2f%n", tax);
        System.out.printf("Total: ₹%.2f%n", total);
    }
}

// Entry point
class PizzaShop {
    public static void main(String[] args) {
        Pizza pizza = PizzaFactory.createPizza("Farmhouse");

        PizzaOrder order = new PizzaOrder.Builder(pizza)
                .size(PizzaSize.LARGE)
                .crust(CrustType.CHEESE_BURST)
                .extraCheese(true)
                .addTopping(Topping.MUSHROOM)
                .addTopping(Topping.OLIVES)
                .build();

        OrderService service = new OrderService();
        service.printSummary(order);
    }
}


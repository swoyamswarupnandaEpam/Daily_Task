public class PizzaShop {
    private static volatile PizzaShop instance = new PizzaShop();

    private PizzaShop() {}
    private String name = "PizzaShop";
    private String description = "Pizza Shop";
    private int taxRate = 10;

    public static PizzaShop getInstance() {
        if (instance == null) {
            synchronized (PizzaShop.class) {
                if (instance == null) {
                    instance = new PizzaShop();
                }
            }
        }
        return instance;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }

}

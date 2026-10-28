public class Pepperoni extends Pizza{
    @Override
    public String type() {
        return "Pepperoni";
    }

    @Override
    public int price() {
        return 350;
    }

    @Override
    public String size() {
        return "large";
    }

    @Override
    public String crust() {
        return "New Base";
    }
}

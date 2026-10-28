public class Margherrita extends Pizza{
    public Margherrita(){}

    @Override
    public String type() {
        return "Margherrita";
    }

    @Override
    public int price() {
        return 200;
    }

    @Override
    public String size() {
        return "Regular";
    }

    @Override
    public String crust() {
        return "New Base";
    }
}

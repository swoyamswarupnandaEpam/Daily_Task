public class Farmhouse extends Pizza{


    @Override
    public String type() {
        return "Farmhouse";
    }

    @Override
    public int price() {
        return 300;
    }

    @Override
    public String size() {
        return "Medium";
    }

    @Override
    public String crust() {
        return "Wheat";
    }
}

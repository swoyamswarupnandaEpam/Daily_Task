public abstract class Pizza {

    public abstract String type();
    public abstract int price();
    public abstract String size();
    public abstract String crust();

    public int getPrice(){
        return price();
    }
}

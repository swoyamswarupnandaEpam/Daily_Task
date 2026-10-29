package org.example;

public class ShopConfig {

    private static ShopConfig instance = new ShopConfig();

    private String shopName = "Tej's Pizza Shop";
    private double taxRate = 0.05;

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

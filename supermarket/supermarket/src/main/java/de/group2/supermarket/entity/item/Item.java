package de.group2.supermarket.entity.item;

import de.group2.supermarket.config.UuidIdentifiedEntity;

public class Item extends UuidIdentifiedEntity {

    private String name;
    private String category;
    private double price;
    private boolean isBasic;
    private double taxRate;

    public Item(String name, String category, double price, boolean isBasic) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.isBasic = isBasic;
        this.taxRate = isBasic ? 7.0 : 19.0; // Setze den Steuersatz basierend auf isBasic
    }

    // Getter-Methoden
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public boolean isBasic() { return isBasic; }
    public double getTaxRate() { return taxRate; }
}

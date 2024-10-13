package de.group2.supermarket.entity;

import de.group2.supermarket.config.UuidIdentifiedEntity;

public class Item extends UuidIdentifiedEntity{

    private String name;
    private String category;
    private double price;
    private int amount;

    
    public Item(String name, String category, double price, int amount) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.amount = amount;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    } 
}

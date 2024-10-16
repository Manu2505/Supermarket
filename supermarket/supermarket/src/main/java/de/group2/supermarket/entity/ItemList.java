package de.group2.supermarket.entity;

import java.util.List;
import de.group2.supermarket.config.UuidIdentifiedEntity;

public class ItemList extends UuidIdentifiedEntity{

    private List<Item> items;
    private double totalPrice;

    public ItemList(List<Item> items, double totalPrice) {
        this.items = items;
        this.totalPrice = totalPrice;
    }

    public List<Item> getItems() {
        return items;
    }
    public void additem(Item item) {
        this.items.add(item);
    }
    public void removeItem(Item item) {
        this.items.remove(item);
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
}
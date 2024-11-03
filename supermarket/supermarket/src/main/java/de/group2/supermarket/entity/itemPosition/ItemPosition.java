package de.group2.supermarket.entity;

import de.group2.supermarket.entity.item.Item;

public class ItemPosition {
    
    private Item item;
    private int amount;
    
    public ItemPosition(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
}

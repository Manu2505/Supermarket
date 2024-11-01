package de.group2.supermarket.entity.receipt;

import de.group2.supermarket.entity.ItemList;

public class ReceiptBuilder {
    
    private String date;
    private String time;
    private String cashier;
    private ItemList itemList;

    // Konstruktor, alle Werte Pflicht
    public ReceiptBuilder(String date, String time, String cashier, ItemList itemList) {
        this.date = date;
        this.time = time;
        this.cashier = cashier;
        this.itemList = itemList;
    }
    
    public ReceiptBuilder setDate(String date) {
        this.date = date;
        return this;
    }
    
    public ReceiptBuilder setTime(String time) {
        this.time = time;
        return this;
    }
    
    public ReceiptBuilder setCashier(String cashier) {
        this.cashier = cashier;
        return this;
    }
    
    public ReceiptBuilder setItemList(ItemList itemList) {
        this.itemList = itemList;
        return this;
    }
    
    public Receipt build() {
        return new Receipt(this);  // Übergabe des Builders an Receipt
    }

    // Getter für den Zugriff durch Receipt
    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getCashier() {
        return cashier;
    }

    public ItemList getItemList() {
        return itemList;
    }
}

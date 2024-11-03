package de.group2.supermarket.entity.receipt;

import de.group2.supermarket.config.UuidIdentifiedEntity;
import de.group2.supermarket.entity.itemList.ItemList;

public class Receipt extends UuidIdentifiedEntity {

    private final String date;
    private final String time;
    private final String cashier;
    private final ItemList itemList;

    // Konstruktor, der alle Felder akzeptiert (für den Builder)
    private Receipt(ReceiptBuilder receiptBuilder) {
        this.date = receiptBuilder.date;
        this.time = receiptBuilder.time;
        this.cashier = receiptBuilder.cashier;
        this.itemList = receiptBuilder.itemList;
    }

    // Getter-Methoden
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


public static class ReceiptBuilder {
    
    private String date;
    private String time;
    private String cashier;
    private ItemList itemList;

    public ReceiptBuilder(String date, String time, String cashier, ItemList itemList){
        this.date = date;
        this.time = time;
        this.cashier = cashier;
        this.itemList = itemList;
    }

    public ReceiptBuilder() {
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
        if (date == null || time == null || cashier == null || itemList == null) {
            throw new IllegalStateException("All fields must be set.");
        }
        return new Receipt(this);
    }
    
}


}

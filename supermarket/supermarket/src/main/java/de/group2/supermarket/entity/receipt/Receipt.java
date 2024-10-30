package de.group2.supermarket.entity.receipt;

import de.group2.supermarket.config.UuidIdentifiedEntity;
import de.group2.supermarket.entity.ItemList;

public class Receipt extends UuidIdentifiedEntity{

    private String date;
    private String time;
    private String cashier;
    private ItemList itemList;
    
    /*public Receipt(ReceiptBuilder builder) {
        this.date = builder.date;
        this.time = builder.time;
        this.cashier = builder.cashier;
        this.itemList = builder.itemList;
    } */
   public Receipt(String date, String time, String cashier, ItemList itemList) {
        this.date = date;
        this.time = time;
        this.cashier = cashier;
        this.itemList = itemList;
    }  

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

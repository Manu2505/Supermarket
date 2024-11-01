package de.group2.supermarket.entity.receipt;

import de.group2.supermarket.entity.ItemList;

public class ReceiptBuilder {
    
    private String date;
    private String time;
    private String cashier;
    private ItemList itemList;

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
        return new Receipt(date, time, cashier, itemList);
    }
}


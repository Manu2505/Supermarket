package de.group2.supermarket.entity.receipt;

import de.group2.supermarket.config.UuidIdentifiedEntity;
import de.group2.supermarket.entity.ItemList;

public class Receipt extends UuidIdentifiedEntity {

    private final String date;
    private final String time;
    private final String cashier;
    private final ItemList itemList;

    // Konstruktor, der nur einen ReceiptBuilder akzeptiert
    public Receipt(ReceiptBuilder builder) {
        this.date = builder.getDate();
        this.time = builder.getTime();
        this.cashier = builder.getCashier();
        this.itemList = builder.getItemList();
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

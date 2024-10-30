package de.group2.supermarket.entity;

import de.group2.supermarket.config.UuidIdentifiedEntity;

public class Receipt extends UuidIdentifiedEntity{

    private String date;
    private String time;
    private String cashier;
    private ItemList itemList;
    
        public Receipt(String date, String time, String cashier, ItemList itemList) {
            this.date = date;
            this.time = time;
            this.cashier = cashier;
            this.itemList = itemList;
        }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public ItemList getItemList() {
        return itemList;
    }

    public void setItemList(ItemList itemList) {
        this.itemList = itemList;
    } 
}

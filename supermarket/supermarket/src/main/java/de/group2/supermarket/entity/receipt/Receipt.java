package de.group2.supermarket.entity.receipt;

import de.group2.supermarket.config.UuidIdentifiedEntity;
import de.group2.supermarket.entity.ItemList;

public class Receipt extends UuidIdentifiedEntity {

    private String date;
    private String time;
    private String cashier;
    private ItemList itemList;

    // Standardkonstruktor für JSON-Deserialisierung
    public Receipt() {
        // Standardkonstruktor kann leer bleiben
    }

    // Konstruktor, der alle Felder akzeptiert (für den Builder)
    public Receipt(String date, String time, String cashier, ItemList itemList) {
        this.date = date;
        this.time = time;
        this.cashier = cashier;
        this.itemList = itemList;
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
    
    // Hier kannst du weiterhin einen Builder anlegen
    public static ReceiptBuilder builder() {
        return new ReceiptBuilder();
    }
}

package de.group2.supermarket.bonprintextended;

import de.group2.supermarket.entity.ItemPosition;

public class POSReceipt extends POSDocument {
    private String title;
    private String address;
    private String phone;

    public void setTitle(String title) {
        this.title = title;
        addStyle(POSStyle.BOLD);
        addStyle(POSStyle.BIG);
        addComponent(() -> (title + "\n").getBytes());
        resetStyle();
    }

    public void setAddress(String address) {
        this.address = address;
        addComponent(() -> (address + "\n").getBytes());
    }

    public void setPhone(String phone) {
        this.phone = phone;
        addComponent(() -> (phone + "\n").getBytes());
    }

    public void addItem(String itemName, double price) {
        addComponent(() -> String.format("%-20s %10.2f\n", itemName, price).getBytes());
    }

    public void additem(String itemName, int quantity, double price, boolean isBasic) {
        double priceWithTax = price * (1 + (isBasic ? 0.07 : 0.19));
        addComponent(
                () -> String.format("%-20s %10.2f\n", itemName, quantity, price, priceWithTax).getBytes());
    }

    // TODO ItemPosition is too long
    public void addItem(ItemPosition itemPosition) {
        double priceWithTax = itemPosition.getItem().getPrice() * (1 + itemPosition.getItem().getTaxRate());
        addComponent(() -> String.format("%-20s %10.2f\n", itemPosition.getItem().getName(), itemPosition.getAmount(), itemPosition.getItem().getPrice(),priceWithTax ).getBytes());
    }

    public void setFooterLine(String footer) {
        addComponent(() -> (footer + "\n\n").getBytes());
    }

    public void addBarcode(POSBarcode barcode) {
        addComponent(barcode);
    }

    public void setTotal(double total) {
        // Feed a bit before printing the total
        addFeed(2);
        addStyle(POSStyle.BOLD);
        addComponent(() -> String.format("Total: %10.2f\n", total).getBytes());
        resetStyle(); // Reset after total
    }
}
package de.group2.supermarket.bonprintextended;

import de.group2.supermarket.entity.itemPosition.ItemPosition;

public class POSReceipt extends POSDocument {

    public void setTitle(String title) {
        addStyle(POSStyle.BOLD);
        addStyle(POSStyle.BIG);
        addComponent((title + "\n")::getBytes);
        resetStyle();
    }

    public void setAddress(String address) {
        addComponent((address + "\n")::getBytes);
    }

    public void setPhone(String phone) {
        addComponent((phone + "\n")::getBytes);
    }

    public void addItem(String itemName, double price) {
        addComponent(() -> String.format("%-20s %10.2f\n", itemName, price).getBytes());
    }

    public void additem(String itemName, int quantity, double price, boolean isBasic) {
        double priceWithTax = price * (1 + (isBasic ? 0.07 : 0.19));
        addComponent(
                () -> String.format("%-20s %10.2f\n", itemName, quantity, price, priceWithTax).getBytes());
    }

    
    public void addItem(ItemPosition itemPosition) {
        double priceWithTax = itemPosition.getItem().getPrice() * (1 + itemPosition.getItem().getTaxRate());
        addComponent(() -> String.format("%-20s %10.2f\n", itemPosition.getItem().getName(), itemPosition.getAmount(), itemPosition.getItem().getPrice(),priceWithTax ).getBytes());
    }

    public void setFooterLine(String footer) {
        addComponent((footer + "\n\n")::getBytes);
    }

    public void setTax(double tax) {
        addStyle(POSStyle.BOLD);
        addComponent(() -> String.format("Tax: %10.2f\n", tax).getBytes());
        resetStyle();
    }

    public void setTotal(double total) {
        // Feed a bit before printing the total
        addStyle(POSStyle.BOLD);
        addComponent(() -> String.format("Total: %10.2f\n", total).getBytes());
        resetStyle(); // Reset after total
    }
}
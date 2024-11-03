package de.group2.supermarket.entity.itemList;

import java.util.List;
import de.group2.supermarket.config.UuidIdentifiedEntity;
import de.group2.supermarket.entity.itemPosition.ItemPosition;

public class ItemList extends UuidIdentifiedEntity {

    private List<ItemPosition> itemPositions;
    private double totalPrice;

    public ItemList(List<ItemPosition> itemPositions, double totalPrice) {
        this.itemPositions = itemPositions;
        this.totalPrice = totalPrice;
    }

    public ItemList() {
    }

    public List<ItemPosition> getItemPositions() {
        return itemPositions;
    }

    public void setItemPositions(List<ItemPosition> itemPositions) {
        this.itemPositions = itemPositions;
    }

    public void additem(ItemPosition itemPosition) {
        this.itemPositions.add(itemPosition);
    }

    public void removeItem(ItemPosition itemPosition) {
        this.itemPositions.remove(itemPosition);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void addToTotalPrice(double totalPrice, ItemPosition itemPosition) {
        this.totalPrice += (itemPosition.getItem().getPrice() * itemPosition.getAmount());
    }

    public void removeFromTotalPrice(double totalPrice, ItemPosition itemPosition) {
        this.totalPrice -= (itemPosition.getItem().getPrice() * itemPosition.getAmount());
    }

}
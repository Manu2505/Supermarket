package de.group2.supermarket.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import de.group2.supermarket.config.UuidIdentifiedEntity;

@Entity
@Table(name = "item_list")
public class ItemList extends UuidIdentifiedEntity {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ItemPosition> itemPositions;

    private double totalPrice;

    public ItemList() {
        // Default constructor for JPA
    }

    public ItemList(List<ItemPosition> itemPositions, double totalPrice) {
        this.itemPositions = itemPositions;
        this.totalPrice = totalPrice;
    }

    public List<ItemPosition> getItemPositions() {
        return itemPositions;
    }

    public void addItem(ItemPosition itemPosition) {
        this.itemPositions.add(itemPosition);
        addToTotalPrice(itemPosition);
    }

    public void removeItem(ItemPosition itemPosition) {
        this.itemPositions.remove(itemPosition);
        removeFromTotalPrice(itemPosition);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void addToTotalPrice(ItemPosition itemPosition) {
        this.totalPrice += (itemPosition.getItem().getPrice() * itemPosition.getAmount());
    }

    public void removeFromTotalPrice(ItemPosition itemPosition) {
        this.totalPrice -= (itemPosition.getItem().getPrice() * itemPosition.getAmount());
    }
}
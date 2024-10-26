package de.group2.supermarket.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

import de.group2.supermarket.config.UuidIdentifiedEntity;

import java.util.UUID;

@Entity
@Table(name = "item")
public class Item extends UuidIdentifiedEntity {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private double price;

    // Default constructor required by JPA
    public Item() {
    }

    public Item(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

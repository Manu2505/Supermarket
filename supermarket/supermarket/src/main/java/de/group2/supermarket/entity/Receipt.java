package de.group2.supermarket.entity;

import de.group2.supermarket.config.UuidIdentifiedEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "receipt")
public class Receipt extends UuidIdentifiedEntity {

    private String date;
    private String time;
    private String cashier;
    private double total;

    // Default constructor required by JPA
    public Receipt() {
    }

    public Receipt(String date, String time, String cashier, double total) {
        this.date = date;
        this.time = time;
        this.cashier = cashier;
        this.total = total;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

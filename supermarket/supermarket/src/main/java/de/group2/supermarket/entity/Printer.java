package de.group2.supermarket.entity;

import de.group2.supermarket.config.UuidIdentifiedEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "printer")
public class Printer extends UuidIdentifiedEntity {

    private String type;
    private String name;
    private int nrOfDesk;

    // Default constructor required by JPA
    public Printer() {
    }

    public Printer(String type, String name, int nrOfDesk) {
        this.type = type;
        this.name = name;
        this.nrOfDesk = nrOfDesk;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNrOfDesk() {
        return nrOfDesk;
    }

    public void setNrOfDesk(int nrOfDesk) {
        this.nrOfDesk = nrOfDesk;
    }
}

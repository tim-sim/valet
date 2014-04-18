package org.tim.domain;

import java.util.Date;

/**
 * @author tim
 */
public class Expense extends Entity {
    private String name;
    private String category;
    private long amount;
    private Date created;

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

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %d, %4$td.%4$tm.%4$tY", name, category, amount, created);
    }
}

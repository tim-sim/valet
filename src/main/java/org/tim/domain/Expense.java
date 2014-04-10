package org.tim.domain;

import java.util.Date;

/**
 * @author tim
 */
public class Expense extends Entity {
    private String title;
    private String category;
    private long amount;
    private Date created;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return String.format("%s, %s, %d, %4$td.%4$tm.%4$tY", title, category, amount, created);
    }
}

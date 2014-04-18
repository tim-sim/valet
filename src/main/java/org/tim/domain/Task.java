package org.tim.domain;

import java.util.Date;

/**
 * @author tim
 */
public class Task extends Entity {
    private String name;
    private Date created = new Date();
    private Date estimate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getEstimate() {
        return estimate;
    }

    public void setEstimate(Date estimate) {
        this.estimate = estimate;
    }

    @Override
    public String toString() {
        return String.format("%s, %2$td.%2$tm.%2$tY", name, estimate);
    }
}

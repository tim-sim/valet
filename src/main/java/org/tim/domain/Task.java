package org.tim.domain;

import java.util.Date;

/**
 * @author tim
 */
public class Task extends Entity {
    private String description;
    private Date created = new Date();
    private Date estimate;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return String.format("%s, %2$td.%2$tm.%2$tY", description, estimate);
    }
}

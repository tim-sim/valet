package org.tim.domain;

import java.util.Date;

/**
 * Created by tim on 3/7/14.
 */
public class Note {
    private String title;
    private String content;
    private Date created = new Date();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}

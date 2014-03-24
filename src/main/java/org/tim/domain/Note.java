package org.tim.domain;

import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;

/**
 * @author tim
 */
public class Note {
    private long id;
    private String content;
    private Date created = new Date();
    private List<Tag> tags = Lists.newArrayList();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}

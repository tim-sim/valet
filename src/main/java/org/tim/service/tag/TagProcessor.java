package org.tim.service.tag;

import org.tim.domain.Tag;

/**
 * @author tim
 */
public interface TagProcessor {
    void handle(Tag tag, String content);
}

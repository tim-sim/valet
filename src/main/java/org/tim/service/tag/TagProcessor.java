package org.tim.service.tag;

import org.tim.domain.Note;

/**
 * @author tim
 */
public interface TagProcessor {
    String getTagName();

    void parse(Note note);
}

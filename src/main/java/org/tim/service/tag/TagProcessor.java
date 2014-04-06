package org.tim.service.tag;

import org.tim.domain.Note;
import org.tim.domain.Tag;

/**
 * @author tim
 */
public interface TagProcessor {
    boolean accepts(Tag tag);

    void parse(Note note);

}

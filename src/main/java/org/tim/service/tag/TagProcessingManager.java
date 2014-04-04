package org.tim.service.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.tim.domain.Note;
import org.tim.domain.Tag;

/**
 * @author tim
 */
public class TagProcessingManager {
    @Autowired
    private TagProcessor[] processors;

    public void processNote(Note note) {
        for (Tag tag : note.getTags()) {
            for (TagProcessor processor : processors) {
                processor.handle(tag, note.getContent());
            }
        }
    }

}

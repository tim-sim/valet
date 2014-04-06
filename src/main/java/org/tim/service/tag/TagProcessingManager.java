package org.tim.service.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tim.domain.Note;
import org.tim.domain.Tag;

/**
 * @author tim
 */
@Service
public class TagProcessingManager {
    @Autowired
    private TagProcessor[] processors;

    public void processNote(Note note) {
        for (Tag tag : note.getTags()) {
            for (TagProcessor processor : processors) {
                if (processor.accepts(tag)) {
                    processor.parse(note);
                }
            }
        }
    }
}

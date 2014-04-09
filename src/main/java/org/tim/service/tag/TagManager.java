package org.tim.service.tag;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tim.domain.Note;
import org.tim.domain.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author tim
 */
@Service
public class TagManager {
    private Map<String, TagProcessor> tagProcessors = Maps.newHashMap();

    @Autowired
    public void setTagProcessors(List<TagProcessor> tagProcessors) {
        this.tagProcessors = Maps.uniqueIndex(tagProcessors, new Function<TagProcessor, String>() {
            @Override
            public String apply(TagProcessor tagProcessor) {
                return tagProcessor.getTagName();
            }
        });
    }

    public List<String> getSuperTags() {
        return new ArrayList<String>(tagProcessors.keySet());
    }

    public void processNote(Note note) {
        for (Tag tag : note.getTags()) {
            TagProcessor tagProcessor = tagProcessors.get(tag.getName());
            if (tagProcessor != null) {
                tagProcessor.parse(note);
            }
        }
    }
}

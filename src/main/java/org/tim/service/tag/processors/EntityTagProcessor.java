package org.tim.service.tag.processors;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import org.tim.dao.EntityDAO;
import org.tim.domain.Entity;
import org.tim.domain.Note;
import org.tim.domain.Tag;
import org.tim.service.tag.TagProcessor;

import java.util.Iterator;
import java.util.Map;

import static org.tim.util.FieldUtils.FieldParser;

/**
 * @author tim
 */
public abstract class EntityTagProcessor<T extends Entity> implements TagProcessor {
    private final Splitter CONTENT_SPLITTER = Splitter.onPattern("[,;]{1}").omitEmptyStrings().trimResults();

    @Override
    public boolean accepts(Tag tag) {
        return getTagName().equals(tag.getName());
    }

    @Override
    public void parse(Note note) {
        Iterator<String> contentIterator = CONTENT_SPLITTER.split(note.getContent()).iterator();
        Iterator<FieldParser> schemeIterator = getFieldScheme().iterator();
        Map<String, Object> data = Maps.newHashMap();
        while (contentIterator.hasNext() && schemeIterator.hasNext()) {
            String contentItem = contentIterator.next();
            FieldParser contentParser = schemeIterator.next();
            data.put(contentParser.getName(), contentParser.getValue(contentItem));
        }
        getDAO().save(createEntity(data));
    }

    protected abstract String getTagName();

    protected abstract Iterable<FieldParser> getFieldScheme();

    protected abstract T createEntity(Map<String, Object> data);

    protected abstract EntityDAO<T> getDAO();
}

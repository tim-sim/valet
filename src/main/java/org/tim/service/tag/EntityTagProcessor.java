package org.tim.service.tag;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import org.tim.dao.EntityDAO;
import org.tim.domain.Entity;
import org.tim.domain.Note;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.tim.util.FieldUtils.FieldParser;

/**
 * @author tim
 */
public abstract class EntityTagProcessor<T extends Entity> implements TagProcessor {
    private final Splitter CONTENT_SPLITTER = Splitter.onPattern("[,;]{1}").omitEmptyStrings().trimResults();

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
        getEntityDAO().save(createEntity(data));
    }

    protected abstract List<FieldParser> getFieldScheme();

    protected abstract T createEntity(Map<String, Object> data);

    protected abstract EntityDAO<T> getEntityDAO();
}

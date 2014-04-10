package org.tim.service.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tim.dao.EntityDAO;
import org.tim.dao.EventsDAO;
import org.tim.domain.Event;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.tim.dao.EventsDAO.*;
import static org.tim.util.FieldUtils.*;

/**
 * @author tim
 */
@Service
public class EventTagProcessor extends EntityTagProcessor<Event> {
    private static final String TAG_EVENT = "event";
    private static final List<FieldParser> EVENT_SCHEME = Arrays.asList(
            simpleParser(FIELD_NAME),
            simpleParser(FIELD_PLACE),
            dateParser(FIELD_DATE)
    );

    private EventsDAO eventsDAO;

    @Override
    protected List<FieldParser> getFieldScheme() {
        return EVENT_SCHEME;
    }

    @Override
    protected Event createEntity(Map<String, Object> data) {
        Event event = new Event();
        event.setName((String) data.get(FIELD_NAME));
        event.setPlace((String) data.get(FIELD_PLACE));
        event.setDate((Date) data.get(FIELD_DATE));
        return event;
    }

    @Override
    protected EntityDAO<Event> getEntityDAO() {
        return eventsDAO;
    }

    @Override
    public String getTagName() {
        return TAG_EVENT;
    }

    @Autowired
    public void setEventsDAO(EventsDAO eventsDAO) {
        this.eventsDAO = eventsDAO;
    }
}

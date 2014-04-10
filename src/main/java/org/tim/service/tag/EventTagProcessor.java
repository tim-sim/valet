package org.tim.service.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tim.dao.EntityDAO;
import org.tim.dao.EventsDAO;
import org.tim.domain.Event;
import org.tim.util.FieldUtils;

import java.util.Map;

/**
 * @author TNasibullin
 */
@Service
public class EventTagProcessor extends EntityTagProcessor<Event> {
    private EventsDAO eventsDAO;

    @Override
    protected Iterable<FieldUtils.FieldParser> getFieldScheme() {
        return null;
    }

    @Override
    protected Event createEntity(Map<String, Object> data) {
        return null;
    }

    @Override
    protected EntityDAO<Event> getEntityDAO() {
        return eventsDAO;
    }

    @Override
    public String getTagName() {
        return "event";
    }

    @Autowired
    public void setEventsDAO(EventsDAO eventsDAO) {
        this.eventsDAO = eventsDAO;
    }
}

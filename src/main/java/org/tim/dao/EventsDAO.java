package org.tim.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.tim.domain.Event;

/**
 * @author TNasibullin
 */
@Repository
public class EventsDAO extends EntityDAO<Event> {
    @Override
    public Event save(Event entity) {
        return null;
    }

    @Override
    protected RowMapper<Event> entityMapper() {
        return null;
    }

    @Override
    protected String entityTable() {
        return "EVENTS";
    }
}

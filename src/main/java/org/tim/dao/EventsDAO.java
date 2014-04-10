package org.tim.dao;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.tim.domain.Event;

import java.sql.*;

/**
 * @author tim
 */
@Repository
public class EventsDAO extends EntityDAO<Event> {
    public static final String TABLE_EVENTS = "EVENTS";
    public static final String FIELD_NAME = "NAME";
    public static final String FIELD_PLACE = "PLACE";
    public static final String FIELD_DATE = "DATE";

    private RowMapper<Event> EVENT_MAPPER = new RowMapper<Event>() {
        @Override
        public Event mapRow(ResultSet resultSet, int i) throws SQLException {
            Event event = new Event();
            event.setId(resultSet.getLong(FIELD_ID));
            event.setName(resultSet.getString(FIELD_NAME));
            event.setPlace(resultSet.getString(FIELD_PLACE));
            event.setDate(resultSet.getDate(FIELD_DATE));
            return event;
        }
    };

    @Override
    public Event save(final Event event) {
        if (event.getId() == 0) {
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement statement = connection.prepareStatement(
                            "insert into EVENTS (NAME, PLACE, DATE) values (?, ?, ?)");
                    statement.setString(1, event.getName());
                    statement.setString(2, event.getPlace());
                    statement.setDate(3, new Date(event.getDate().getTime()));
                    return statement;
                }
            }, keyHolder);
            event.setId(keyHolder.getKey().longValue());
        } else {
            jdbcTemplate.update("update EVENTS set NAME = ?, PLACE = ?, DATE = ? where ID = ?",
                    event.getName(), event.getPlace(), event.getDate(), event.getId());
        }
        return event;
    }

    @Override
    protected RowMapper<Event> entityMapper() {
        return EVENT_MAPPER;
    }

    @Override
    protected String entityTable() {
        return TABLE_EVENTS;
    }

}

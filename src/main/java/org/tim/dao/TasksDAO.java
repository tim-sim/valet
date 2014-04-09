package org.tim.dao;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.tim.domain.Task;

import java.sql.*;

/**
 * @author tim
 */
@Repository
public class TasksDAO extends EntityDAO<Task> {
    public static final String TABLE_TASKS = "TASKS";
    public static final String FIELD_DESCRIPTION = "DESCRIPTION";
    public static final String FIELD_CREATED = "CREATED";
    public static final String FIELD_ESTIMATE = "ESTIMATE";

    private static final RowMapper<Task> TASK_MAPPER = new RowMapper<Task>() {
        @Override
        public Task mapRow(ResultSet resultSet, int i) throws SQLException {
            Task task = new Task();
            task.setId(resultSet.getLong("ID"));
            task.setDescription(resultSet.getString(FIELD_DESCRIPTION));
            task.setCreated(resultSet.getDate(FIELD_CREATED));
            task.setEstimate(resultSet.getDate(FIELD_ESTIMATE));
            return task;
        }
    };

    @Override
    public Task save(final Task task) {
        if (task.getId() == 0) {
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement statement = connection.prepareStatement(
                            "insert into TASKS (DESCRIPTION, CREATED, ESTIMATE) values (?, ?, ?)");
                    statement.setString(1, task.getDescription());
                    statement.setDate(2, new Date(task.getCreated().getTime()));
                    statement.setDate(3, new Date(task.getEstimate().getTime()));
                    return statement;
                }
            }, keyHolder);
            task.setId(keyHolder.getKey().longValue());
        } else {
            jdbcTemplate.update("update TASKS set DESCRIPTION = ?, CREATED = ?, ESTIMATE = ? where ID = ?",
                    task.getDescription(), task.getCreated(), task.getEstimate(), task.getId());
        }
        return task;
    }

    @Override
    protected RowMapper<Task> entityMapper() {
        return TASK_MAPPER;
    }

    @Override
    protected String entityTable() {
        return TABLE_TASKS;
    }
}

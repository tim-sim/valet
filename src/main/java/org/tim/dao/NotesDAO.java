package org.tim.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.tim.domain.Note;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * @author tim
 */
@Repository
public class NotesDAO {
    private static RowMapper<Note> NOTE_MAPPER = new RowMapper<Note>() {
        @Override
        public Note mapRow(ResultSet resultSet, int i) throws SQLException {
            Note note = new Note();
            note.setId(resultSet.getLong("id"));
            note.setContent(resultSet.getString("content"));
            note.setCreated(resultSet.getDate("created"));
            return note;
        }
    };
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Note> findAll() {
        return jdbcTemplate.query("select * from notes", NOTE_MAPPER);
    }

    public List<Note> findByTagId(Long tagId) {
        return jdbcTemplate.query(
                "select n.* from NOTES n, NOTES_TAGS nt " +
                        "where n.ID = nt.NOTE_ID " +
                        "and nt.TAG_ID = ?",
                NOTE_MAPPER, tagId
        );
    }

    public List<Note> findByTagName(String tagName) {
        return jdbcTemplate.query(
                "select n.* from NOTES n, NOTES_TAGS nt, TAGS t " +
                        "where n.ID = nt.NOTE_ID " +
                        "and nt.TAG_ID = t.ID " +
                        "and t.NAME = ?",
                NOTE_MAPPER, tagName
        );
    }

    public void create(final Note note) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement = connection.prepareStatement("insert into NOTES (CONTENT, CREATED) values (?, ?)");
                statement.setString(1, note.getContent());
                statement.setDate(2, new Date(note.getCreated().getTime()));
                return statement;
            }
        }, keyHolder);
        note.setId(keyHolder.getKey().longValue());
    }

    public void delete(long id) {
        jdbcTemplate.update("delete from notes where id = ?", id);
    }

    public Note findById(long id) {
        List<Note> notes = jdbcTemplate.query("select * from NOTES where ID = ? limit 1", NOTE_MAPPER, id);
        return notes != null ? notes.get(0) : null;
    }
}

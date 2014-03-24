package org.tim.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.tim.domain.Note;
import org.tim.domain.Tag;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * @author tim
 */
@Repository
public class NotesDAO {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TagsDAO tagsDAO;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Note> findAllNotes() {
        return jdbcTemplate.query("select * from notes", NOTE_MAPPER);
    }

    public List<Note> findByTag(Long tagId) {
        return jdbcTemplate.query("select n.* from NOTES n, NOTES_TAGS nt where nt.NOTE_ID = n.ID and nt.TAG_ID = ?", NOTE_MAPPER, tagId);
    }

    public void create(final Note note) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement = connection.prepareStatement("insert into notes (content, created) values (?, ?)");
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
}
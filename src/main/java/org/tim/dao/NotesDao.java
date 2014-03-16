package org.tim.dao;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.tim.domain.Note;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author TNasibullin
 */
@Repository
public class NotesDao {
    private static final NoteMapper NOTE_MAPPER = new NoteMapper();
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static List<Note> notes = Lists.newArrayList();
    static {
        notes.add(testNote());
        notes.add(testNote());
        notes.add(testNote());
    }

    public List<Note> getAllNotes() {
        return jdbcTemplate.query("select * from notes", NOTE_MAPPER);
    }

    public void create(Note note) {
        jdbcTemplate.update("insert into notes (title, content, created) values (?, ?, ?)", note.getTitle(), note.getContent(), note.getCreated());
    }

    public void delete(long id) {
        jdbcTemplate.update("delete from notes where id = ?", id);
    }

    private static Note testNote() {
        Note note = new Note();
        note.setTitle(RandomStringUtils.randomAlphabetic(5));
        note.setContent(RandomStringUtils.randomAlphabetic(15));
        return note;
    }

    private static class NoteMapper implements RowMapper<Note> {
        @Override
        public Note mapRow(ResultSet resultSet, int i) throws SQLException {
            Note note = new Note();
            note.setTitle(resultSet.getString("title"));
            note.setContent(resultSet.getString("content"));
            note.setCreated(resultSet.getDate("created"));
            return note;
        }
    }
}

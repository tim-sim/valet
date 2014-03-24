package org.tim.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.tim.domain.Note;
import org.tim.domain.Tag;

import javax.sql.DataSource;

/**
 * @author tim
 */
@Repository
public class NotesTagsDAO extends BaseDAO {
    public void link(Note note, Tag tag) {
        jdbcTemplate.update("insert into NOTES_TAGS (NOTE_ID, TAG_ID) values (?, ?)", note.getId(), tag.getId());
    }

    public void unlinkNote(long noteId) {
        jdbcTemplate.update("delete from NOTES_TAGS where NOTE_ID = ?", noteId);
    }

    public void unlinkTag(long tagId) {
        jdbcTemplate.update("delete from NOTES_TAGS where TAG_ID = ?", tagId);
    }
}

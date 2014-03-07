package org.tim.dao;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Repository;
import org.tim.domain.Note;

import java.util.List;

/**
 * @author TNasibullin
 */
@Repository
public class NotesDao {
    private static List<Note> notes = Lists.newArrayList();
    static {
        notes.add(testNote());
        notes.add(testNote());
        notes.add(testNote());
    }

    public List<Note> getAllNotes() {
        return notes;
    }

    public void create(Note note) {
        notes.add(note);
    }

    public void delete(long id) {
        notes.remove(id);
    }

    private static Note testNote() {
        Note note = new Note();
        note.setTitle(RandomStringUtils.randomAlphabetic(5));
        note.setContent(RandomStringUtils.randomAlphabetic(15));
        return note;
    }
}

package org.tim.service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tim.dao.NotesDao;
import org.tim.domain.Note;

import java.util.List;

/**
 * Created by tim on 3/7/14.
 */
@Service
public class NotesService {
    @Autowired
    private NotesDao notesDao;

    public List<Note> getAllNotes() {
        return notesDao.getAllNotes();
    }

    public void addNote(Note note) {
        notesDao.create(note);
    }

    public void removeNote(long id) {
        notesDao.delete(id);
    }
}

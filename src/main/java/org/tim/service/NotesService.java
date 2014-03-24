package org.tim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tim.dao.NotesDAO;
import org.tim.dao.NotesTagsDAO;
import org.tim.dao.TagsDAO;
import org.tim.domain.Note;
import org.tim.domain.Tag;

import java.util.List;

/**
 * @author tim
 */
@Service
public class NotesService {
    @Autowired
    private NotesDAO notesDAO;
    @Autowired
    private TagsDAO tagsDAO;
    @Autowired
    private NotesTagsDAO notesTagsDAO;

    public List<Note> getAllNotes() {
        List<Note> notes = notesDAO.findAllNotes();
        for (Note note : notes) {
            note.setTags(tagsDAO.findByNote(note.getId()));
        }
        return notes;
    }

/*
    public Note getById(long id) {
        Note note = notesDAO.findById(id);
        note.setTags(tagsDAO.findByNote(note.getId()));
        return note;
    }
*/

    public void addNote(Note note) {
        notesDAO.create(note);
        for (Tag tag : note.getTags()) {
            tagsDAO.merge(tag);
            notesTagsDAO.link(note, tag);
        }
    }

    public void removeNote(long id) {
        notesTagsDAO.unlinkNote(id);
        notesDAO.delete(id);
    }
}

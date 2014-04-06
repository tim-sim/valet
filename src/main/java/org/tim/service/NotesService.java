package org.tim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tim.dao.NotesDAO;
import org.tim.dao.NotesTagsDAO;
import org.tim.dao.TagsDAO;
import org.tim.domain.Note;
import org.tim.domain.Tag;
import org.tim.service.tag.TagProcessingManager;

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
    @Autowired
    private TagProcessingManager tagManager;

    public List<Note> getAllNotes() {
        return populateTags(notesDAO.findAll());
    }

    public List<Note> getNotesByTag(long tagId) {
        return populateTags(notesDAO.findByTagId(tagId));
    }


    public Note getById(long id) {
        Note note = notesDAO.findById(id);
        note.setTags(tagsDAO.findByNote(note.getId()));
        return note;
    }

    public void addNote(Note note) {
        notesDAO.create(note);
        for (Tag tag : note.getTags()) {
            tagsDAO.merge(tag);
            notesTagsDAO.link(note, tag);
        }
        tagManager.processNote(note);
    }

    public void removeNote(Note note) {
        notesTagsDAO.unlinkNote(note.getId());
        notesDAO.delete(note.getId());
        for (Tag tag : note.getTags()) {
            tagsDAO.deleteOrphan(tag.getId());
        }
    }

    public List<Tag> getAllTags() {
        return tagsDAO.finAll();
    }

    private List<Note> populateTags(List<Note> notes) {
        for (Note note : notes) {
            note.setTags(tagsDAO.findByNote(note.getId()));
        }
        return notes;
    }
}

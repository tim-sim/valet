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
public class WriteDataService {
    private NotesDAO notesDAO;
    private TagsDAO tagsDAO;
    private NotesTagsDAO notesTagsDAO;

    @Autowired
    public void setNotesDAO(NotesDAO notesDAO) {
        this.notesDAO = notesDAO;
    }

    @Autowired
    public void setTagsDAO(TagsDAO tagsDAO) {
        this.tagsDAO = tagsDAO;
    }

    @Autowired
    public void setNotesTagsDAO(NotesTagsDAO notesTagsDAO) {
        this.notesTagsDAO = notesTagsDAO;
    }

    public void addNote(Note note) {
        notesDAO.create(note);
        for (Tag tag : note.getTags()) {
            tagsDAO.save(tag);
            notesTagsDAO.link(note, tag);
        }
    }

    public void removeNote(long noteId) {
        List<Tag> tags = tagsDAO.findByNote(noteId);
        notesTagsDAO.unlinkNote(noteId);
        notesDAO.delete(noteId);
        for (Tag tag : tags) {
            tagsDAO.deleteOrphan(tag.getId());
        }
    }
}

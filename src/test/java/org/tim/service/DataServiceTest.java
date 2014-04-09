package org.tim.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tim.dao.NotesDAO;
import org.tim.dao.NotesTagsDAO;
import org.tim.dao.TagsDAO;
import org.tim.domain.Note;
import org.tim.domain.Tag;
import org.tim.service.tag.BaseDbTest;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author tim
 */
public class DataServiceTest extends BaseDbTest {
    private ReadDataService readDataService = new ReadDataService();
    private WriteDataService writeDataService = new WriteDataService();
    private NotesDAO notesDAO = new NotesDAO();
    private TagsDAO tagsDAO = new TagsDAO();
    private NotesTagsDAO notesTagsDAO = new NotesTagsDAO();

    @Before
    public void setUp() {
        super.setUp();
        notesDAO.setDataSource(dataSource);
        tagsDAO.setDataSource(dataSource);
        notesTagsDAO.setDataSource(dataSource);

        readDataService.setNotesDAO(notesDAO);
        readDataService.setTagsDAO(tagsDAO);
        writeDataService.setNotesDAO(notesDAO);
        writeDataService.setTagsDAO(tagsDAO);
        writeDataService.setNotesTagsDAO(notesTagsDAO);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddNote() {
        Tag tag1 = new Tag("tag1");
        Tag tag2 = new Tag("tag2");
        Note note1 = new Note();
        note1.setContent("note1");
        note1.setCreated(getDate(0));
        note1.setTags(Arrays.asList(tag1, tag2));

        writeDataService.addNote(note1);
        List<Note> notes = readDataService.getAllNotes();
        Note savedNote1 = notes.get(0);
        assertEquals(note1, savedNote1);
    }

    private void assertEquals(Note expected, Note actual) {
        Assert.assertEquals(expected.getContent(), actual.getContent());
        Assert.assertEquals(expected.getCreated(), actual.getCreated());
        Iterator<Tag> expectedTags = expected.getTags().iterator();
        Iterator<Tag> actualTags = actual.getTags().iterator();
        while (expectedTags.hasNext() && actualTags.hasNext()) {
            Assert.assertEquals(expectedTags.next().getName(), actualTags.next().getName());
        }
    }
}

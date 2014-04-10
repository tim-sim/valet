package org.tim.service.tag;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tim.dao.EventsDAO;
import org.tim.domain.Event;
import org.tim.domain.Note;
import org.tim.domain.Tag;

import java.util.Arrays;

/**
 * @author tim
 */
public class EventTagProcessorTest extends BaseDbTest {
    private EventsDAO eventsDAO = new EventsDAO();
    private EventTagProcessor tagProcessor = new EventTagProcessor();

    @Before
    public void setUp() {
        super.setUp();
        eventsDAO.setDataSource(dataSource);
        tagProcessor.setEventsDAO(eventsDAO);
    }

    @After
    public void tearDown() {
        for (Event event : eventsDAO.getAll()) {
            eventsDAO.delete(event.getId());
        }
    }

    @Test
    public void testParse() {
        Event event = testEvent();
        Note note = testNote(event);

        tagProcessor.parse(note);

        Event savedEvent = eventsDAO.getAll().get(0);
        assertEquals(event, savedEvent);
    }

    private Note testNote(Event event) {
        Note note = new Note();
        note.setContent(event.toString());
        note.setTags(Arrays.asList(new Tag("event")));
        note.setCreated(getDate(0));
        return note;
    }

    private Event testEvent() {
        Event event = new Event();
        event.setName("test event");
        event.setPlace("somewhere");
        event.setDate(getDate(1));
        return event;
    }

    private void assertEquals(Event expected, Event actual) {
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getPlace(), actual.getPlace());
        Assert.assertEquals(expected.getDate(), actual.getDate());
    }
}

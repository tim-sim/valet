package org.tim.service.tag;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tim.dao.TasksDAO;
import org.tim.domain.Note;
import org.tim.domain.Tag;
import org.tim.domain.Task;

import java.util.Arrays;

/**
 * @author tim
 */
public class TaskTagProcessorTest extends BaseDbTest {
    private TaskTagProcessor tagProcessor = new TaskTagProcessor();
    private TasksDAO tasksDAO = new TasksDAO();

    @Before
    public void setUp() {
        super.setUp();
        tasksDAO.setDataSource(dataSource);
        tagProcessor.setTasksDAO(tasksDAO);
    }

    @After
    public void tearDown() {
        for (Task task : tasksDAO.getAll()) {
            tasksDAO.delete(task.getId());
        }
    }

    @Test
    public void testParse() throws Exception {
        Task task = testTask();

        Note note = testNote(task);

        tagProcessor.parse(note);

        Task savedTask = tasksDAO.getAll().get(0);
        assertEquals(task, savedTask);
    }

    private Note testNote(Task task) {
        Note note = new Note();
        note.setTags(Arrays.asList(new Tag("task")));
        note.setContent(task.toString());
        return note;
    }

    private Task testTask() {
        Task task = new Task();
        task.setName("some task");
        task.setEstimate(getDate(10));
        return task;
    }

    private void assertEquals(Task expected, Task actual) {
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getEstimate(), actual.getEstimate());
    }
}

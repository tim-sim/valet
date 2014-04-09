package org.tim.service.tag.processors;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tim.dao.TasksDAO;
import org.tim.domain.Note;
import org.tim.domain.Tag;
import org.tim.domain.Task;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author tim
 */
public class TaskTagProcessorTest extends BaseDbTest {
    private static final String DESCRIPTION = "task description";

    private TaskTagProcessor tagProcessor = new TaskTagProcessor();
    private TasksDAO tasksDAO = new TasksDAO();

    @Before
    public void setUp() {
        DataSource dataSource = createDataSource();
        tasksDAO.setDataSource(dataSource);
        tagProcessor.setTasksDAO(tasksDAO);
    }

    @After
    public void tearDown() {
        List<Task> tasks = tasksDAO.getAll();
        for (Task task : tasks) {
            tasksDAO.delete(task.getId());
        }
    }

    @Test
    public void testParse() throws Exception {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date estimate = calendar.getTime();
        String estimateString = new SimpleDateFormat("dd.MM.yyyy").format(calendar.getTime());

        Note note = new Note();
        note.getTags().add(new Tag("task"));
        note.setContent(DESCRIPTION + "," + estimateString);

        tagProcessor.parse(note);

        List<Task> tasks = tasksDAO.getAll();
        Task task = tasks.get(0);
        Assert.assertEquals(DESCRIPTION, task.getDescription());
        Assert.assertEquals(estimate, task.getEstimate());
    }
}

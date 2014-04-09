package org.tim.service.tag.processors;

import org.springframework.beans.factory.annotation.Autowired;
import org.tim.dao.EntityDAO;
import org.tim.dao.TasksDAO;
import org.tim.domain.Task;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.tim.util.FieldUtils.*;

/**
 * @author tim
 */
public class TaskTagProcessor extends EntityTagProcessor<Task> {
    private final List<FieldParser> TASK_SCHEME = Arrays.asList(
            simpleParser("description"),
            dateParser("estimate")
    );

    @Autowired
    private TasksDAO tasksDAO;
    @Override
    protected String getTagName() {
        return "task";
    }

    @Override
    protected Iterable<FieldParser> getFieldScheme() {
        return TASK_SCHEME;
    }

    @Override
    protected Task createEntity(Map<String, Object> data) {
        Task task = new Task();
        task.setDescription((String) data.get("description"));
        task.setEstimate((Date) data.get("estimate"));
        return task;
    }

    @Override
    protected EntityDAO<Task> getDAO() {
        return tasksDAO;
    }

    public void setTasksDAO(TasksDAO tasksDAO) {
        this.tasksDAO = tasksDAO;
    }
}

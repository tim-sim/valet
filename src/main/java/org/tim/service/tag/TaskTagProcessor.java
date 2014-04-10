package org.tim.service.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
@Service
public class TaskTagProcessor extends EntityTagProcessor<Task> {
    private final List<FieldParser> TASK_SCHEME = Arrays.asList(
            simpleParser(TasksDAO.FIELD_DESCRIPTION),
            dateParser(TasksDAO.FIELD_ESTIMATE)
    );

    private TasksDAO tasksDAO;

    @Override
    protected List<FieldParser> getFieldScheme() {
        return TASK_SCHEME;
    }

    @Override
    protected Task createEntity(Map<String, Object> data) {
        Task task = new Task();
        task.setDescription((String) data.get(TasksDAO.FIELD_DESCRIPTION));
        task.setEstimate((Date) data.get(TasksDAO.FIELD_ESTIMATE));
        return task;
    }

    @Override
    protected EntityDAO<Task> getEntityDAO() {
        return tasksDAO;
    }

    @Override
    public String getTagName() {
        return "task";
    }

    @Autowired
    public void setTasksDAO(TasksDAO tasksDAO) {
        this.tasksDAO = tasksDAO;
    }
}

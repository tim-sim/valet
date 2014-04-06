package org.tim.service.tag.processors;

import org.tim.dao.EntityDAO;
import org.tim.domain.Task;
import org.tim.util.FieldUtils;

import java.util.Map;

/**
 * @author tim
 */
public class TaskTagProcessor extends EntityTagProcessor<Task> {
    @Override
    public String getTagName() {
        return "task";
    }

    @Override
    public Iterable<FieldUtils.FieldParser> getFieldScheme() {
        return null;
    }

    @Override
    public Task createEntity(Map<String, Object> data) {
        Task task = new Task();
        return task;
    }

    @Override
    public EntityDAO<Task> getDAO() {
        return null;
    }

}

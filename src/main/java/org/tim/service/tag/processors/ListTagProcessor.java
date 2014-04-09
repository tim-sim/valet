package org.tim.service.tag.processors;

import org.springframework.stereotype.Service;
import org.tim.dao.EntityDAO;
import org.tim.domain.List;
import org.tim.util.FieldUtils;

import java.util.Map;

/**
 * @author tim
 */
@Service
public class ListTagProcessor extends EntityTagProcessor<List> {
    @Override
    public String getTagName() {
        return "list";
    }

    @Override
    public Iterable<FieldUtils.FieldParser> getFieldScheme() {
        return null;
    }

    @Override
    public List createEntity(Map<String, Object> data) {
        return null;
    }

    @Override
    public EntityDAO<List> getDAO() {
        return null;
    }
}

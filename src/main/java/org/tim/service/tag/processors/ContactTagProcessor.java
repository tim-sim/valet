package org.tim.service.tag.processors;

import org.tim.dao.EntityDAO;
import org.tim.domain.Contact;
import org.tim.util.FieldUtils;

import java.util.Map;

/**
 * @author tim
 */
public class ContactTagProcessor extends EntityTagProcessor<Contact> {
    @Override
    public String getTagName() {
        return "contact";
    }

    @Override
    public Iterable<FieldUtils.FieldParser> getFieldScheme() {
        return null;
    }

    @Override
    public Contact createEntity(Map<String, Object> data) {
        return null;
    }

    @Override
    public EntityDAO<Contact> getDAO() {
        return null;
    }
}

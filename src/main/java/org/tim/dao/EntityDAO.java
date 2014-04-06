package org.tim.dao;

import org.tim.domain.Entity;

import java.util.List;

/**
 * @author tim
 */
public abstract class EntityDAO<T extends Entity> extends BaseDAO {
    public abstract List<T> getAll();

    public abstract T getById(long id);

    public abstract T save(T entity);
}

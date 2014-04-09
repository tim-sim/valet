package org.tim.dao;

import org.springframework.jdbc.core.RowMapper;
import org.tim.domain.Account;
import org.tim.domain.Entity;

import java.util.List;

/**
 * @author tim
 */
public abstract class EntityDAO<T extends Entity> extends BaseDAO {
    public List<T> getAll() {
        return jdbcTemplate.query(String.format("select * from %s", entityTable()), entityMapper());
    }

    public T getById(long id) {
        List<T> entities = jdbcTemplate.query(String.format("select * from %s where ID = %d", entityTable(), id), entityMapper());
        return (entities != null && entities.size() > 0) ? entities.get(0) : null;
    }

    public void delete(long id) {
        jdbcTemplate.update(String.format("delete from %s where ID = %d", entityTable(), id));
    }

    public abstract T save(T entity);

    protected abstract RowMapper<T> entityMapper();

    protected abstract String entityTable();
}

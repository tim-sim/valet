package org.tim.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.tim.domain.Expense;

/**
 * @author TNasibullin
 */
@Repository
public class ExpensesDAO extends EntityDAO<Expense> {
    public static final String TABLE_EXPENSES = "EXPENSES";

    @Override
    public Expense save(Expense entity) {
        return null;
    }

    @Override
    protected RowMapper<Expense> entityMapper() {
        return null;
    }

    @Override
    protected String entityTable() {
        return TABLE_EXPENSES;
    }
}

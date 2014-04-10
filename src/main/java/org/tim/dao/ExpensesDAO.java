package org.tim.dao;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.tim.domain.Expense;

import java.sql.*;

/**
 * @author tim
 */
@Repository
public class ExpensesDAO extends EntityDAO<Expense> {
    public static final String TABLE_EXPENSES = "EXPENSES";
    public static final String FIELD_TITLE = "TITLE";
    public static final String FIELD_AMOUNT = "AMOUNT";
    public static final String FIELD_CATEGORY = "CATEGORY";
    public static final String FIELD_CREATED = "CREATED";

    private static final RowMapper<Expense> EXPENSE_MAPPER = new RowMapper<Expense>() {
        @Override
        public Expense mapRow(ResultSet resultSet, int i) throws SQLException {
            Expense expense = new Expense();
            expense.setId(resultSet.getLong(FIELD_ID));
            expense.setTitle(resultSet.getString(FIELD_TITLE));
            expense.setCategory(resultSet.getString(FIELD_CATEGORY));
            expense.setAmount(resultSet.getLong(FIELD_AMOUNT));
            expense.setCreated(resultSet.getDate(FIELD_CREATED));
            return expense;
        }
    };

    @Override
    public Expense save(final Expense expense) {
        if (expense.getId() == 0) {
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement statement = connection.prepareStatement(
                            "insert into EXPENSES (TITLE, CATEGORY, AMOUNT, CREATED) values (?, ?, ?, ?)");
                    statement.setString(1, expense.getTitle());
                    statement.setString(2, expense.getCategory());
                    statement.setLong(3, expense.getAmount());
                    statement.setDate(4, new Date(expense.getCreated().getTime()));
                    return statement;
                }
            }, keyHolder);
            expense.setId(keyHolder.getKey().longValue());
        } else {
            jdbcTemplate.update("update EXPENSES set TITLE = ?, CATEGORY = ?, AMOUNT = ?, CREATED = ? where ID = ?",
                    expense.getTitle(), expense.getCategory(), expense.getAmount(), expense.getCreated(), expense.getId());
        }
        return expense;
    }

    @Override
    protected RowMapper<Expense> entityMapper() {
        return EXPENSE_MAPPER;
    }

    @Override
    protected String entityTable() {
        return TABLE_EXPENSES;
    }
}

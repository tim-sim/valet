package org.tim.dao;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.tim.domain.Account;

import java.sql.*;

/**
 * @author tim
 */
@Repository
public class AccountsDAO extends EntityDAO<Account> {
    public static final String TABLE_ACCOUNTS = "ACCOUNTS";
    public static final String FIELD_BANK = "BANK";
    public static final String FIELD_AMOUNT = "AMOUNT";
    public static final String FIELD_PERCENT = "PERCENT";
    public static final String FIELD_EXPIRE = "EXPIRE";

    private static final RowMapper<Account> ACCOUNT_MAPPER = new RowMapper<Account>() {
        @Override
        public Account mapRow(ResultSet resultSet, int i) throws SQLException {
            Account account = new Account();
            account.setId(resultSet.getLong("ID"));
            account.setBank(resultSet.getString(FIELD_BANK));
            account.setAmount(resultSet.getLong(FIELD_AMOUNT));
            account.setPercent(resultSet.getInt(FIELD_PERCENT));
            account.setExpire(resultSet.getDate(FIELD_EXPIRE));
            return account;
        }
    };

    @Override
    public Account save(final Account account) {
        if (account.getId() == 0) {
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement statement = connection.prepareStatement("insert into ACCOUNTS (BANK, AMOUNT, PERCENT, EXPIRE) values (?, ?, ?, ?)");
                    statement.setString(1, account.getBank());
                    statement.setLong(2, account.getAmount());
                    statement.setInt(3, account.getPercent());
                    statement.setDate(4, account.getExpire() != null ? new Date(account.getExpire().getTime()) : null);
                    return statement;
                }
            }, keyHolder);
            account.setId(keyHolder.getKey().longValue());
        } else {
            jdbcTemplate.update("update ACCOUNTS set BANK = ?, AMOUNT = ?, PERCENT = ?, EXPIRE = ? where ID = ?",
                    account.getBank(), account.getAmount(), account.getPercent(), account.getExpire(), account.getId());
        }
        return account;
    }

    @Override
    protected RowMapper<Account> entityMapper() {
        return ACCOUNT_MAPPER;
    }

    @Override
    protected String entityTable() {
        return TABLE_ACCOUNTS;
    }
}

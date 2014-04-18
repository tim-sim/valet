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
    public static final String FIELD_BANKNAME = "BANKNAME";
    public static final String FIELD_AMOUNT = "AMOUNT";
    public static final String FIELD_RATE = "RATE";
    public static final String FIELD_EXPIRE = "EXPIRE";

    private static final RowMapper<Account> ACCOUNT_MAPPER = new RowMapper<Account>() {
        @Override
        public Account mapRow(ResultSet resultSet, int i) throws SQLException {
            Account account = new Account();
            account.setId(resultSet.getLong(FIELD_ID));
            account.setBankname(resultSet.getString(FIELD_BANKNAME));
            account.setAmount(resultSet.getLong(FIELD_AMOUNT));
            account.setRate(resultSet.getInt(FIELD_RATE));
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
                    PreparedStatement statement = connection.prepareStatement(
                            String.format("insert into %s (%s, %s, %s, %s) values (?, ?, ?, ?)",
                            TABLE_ACCOUNTS, FIELD_BANKNAME, FIELD_AMOUNT, FIELD_RATE, FIELD_EXPIRE));
                    statement.setString(1, account.getBankname());
                    statement.setLong(2, account.getAmount());
                    statement.setInt(3, account.getRate());
                    statement.setDate(4, account.getExpire() != null ? new Date(account.getExpire().getTime()) : null);
                    return statement;
                }
            }, keyHolder);
            account.setId(keyHolder.getKey().longValue());
        } else {
            jdbcTemplate.update(
                    String.format("update %s set %s = ?, %s = ?, %s = ?, %s = ? where %s = ?",
                    TABLE_ACCOUNTS, FIELD_BANKNAME, FIELD_AMOUNT, FIELD_RATE, FIELD_EXPIRE, FIELD_ID),
                    account.getBankname(), account.getAmount(), account.getRate(), account.getExpire(), account.getId());
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

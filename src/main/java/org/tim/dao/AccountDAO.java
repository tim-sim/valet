package org.tim.dao;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.tim.domain.Account;

import java.sql.*;
import java.util.List;

/**
 * @author tim
 */
@Repository
public class AccountDAO extends EntityDAO<Account> {
    private static final RowMapper<Account> ACCOUNT_MAPPER = new RowMapper<Account>() {
        @Override
        public Account mapRow(ResultSet resultSet, int i) throws SQLException {
            Account account = new Account();
            account.setId(resultSet.getLong("ID"));
            account.setBank(resultSet.getString("BANK"));
            account.setAmount(resultSet.getLong("AMOUNT"));
            account.setPercent(resultSet.getInt("PERCENT"));
            account.setExpire(resultSet.getDate("EXPIRE"));
            return account;
        }
    };

    @Override
    public List<Account> getAll() {
        return jdbcTemplate.query("select * from ACCOUNTS", ACCOUNT_MAPPER);
    }

    @Override
    public Account getById(long id) {
        List<Account> accounts = jdbcTemplate.query("select * from ACCOUNTS where ID = ?", ACCOUNT_MAPPER, id);
        return (accounts != null && accounts.size() > 0) ? accounts.get(0) : null;
    }

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
                    statement.setDate(4, new Date(account.getExpire().getTime()));
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
}

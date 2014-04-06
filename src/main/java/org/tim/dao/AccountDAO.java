package org.tim.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.tim.domain.Account;

import java.sql.ResultSet;
import java.sql.SQLException;
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
            account.setId(resultSet.getLong("id"));
            account.setBank(resultSet.getString("bank"));
            account.setAmount(resultSet.getLong("amount"));
            account.setPercent(resultSet.getInt("percent"));
            account.setExpire(resultSet.getDate("expire"));
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
    public Account save(Account account) {
        if (account.getId() == 0) {
            jdbcTemplate.update("insert into ACCOUNTS () values ()");
        } else {
            jdbcTemplate.update("update ACCOUNTS set where ID = ?");
        }
        return null;
    }
}

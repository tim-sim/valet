package org.tim.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.tim.domain.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author tim
 */
@Repository
public class UsersDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(final User user) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement = connection.prepareStatement(
                        "insert into USERS (USERNAME, PASSWORD, FIRSTNAME, SECONDNAME, EMAIL) values (?, ?, ?, ?, ?)");
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getFirstName());
                statement.setString(4, user.getSecondName());
                statement.setString(5, user.getEmail());
                return statement;
            }
        }, keyHolder);
        user.setId(keyHolder.getKey().longValue());
    }

    public void update(User user) {

    }

    public void delete(long userId) {

    }

    private static RowMapper<User> USER_MAPPER = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setFirstName(resultSet.getString("firstName"));
            user.setSecondName(resultSet.getString("secondName"));
            user.setEmail(resultSet.getString("email"));
            return user;
        }
    };
}

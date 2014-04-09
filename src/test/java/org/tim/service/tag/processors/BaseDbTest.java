package org.tim.service.tag.processors;

import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;

/**
 * @author tim
 */
public class BaseDbTest {
    private static final String H2_URL = "jdbc:h2:~/test";
    private static final String H2_USER = "test";
    private static final String H2_PASS = "test";

    protected DataSource createDataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(H2_URL);
        dataSource.setUser(H2_USER);
        dataSource.setPassword(H2_PASS);
        return dataSource;
    }
}

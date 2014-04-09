package org.tim.service.tag.processors;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.After;
import org.junit.Before;
import org.tim.dao.NotesDAO;

import javax.sql.DataSource;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author tim
 */
public class BaseDbTest {
    private static final String H2_URL = "jdbc:h2:~/test";
    private static final String H2_USER = "test";
    private static final String H2_PASS = "test";

    protected JdbcDataSource dataSource = new JdbcDataSource();

    @Before
    public void setUp() {
        dataSource.setURL(H2_URL);
        dataSource.setUser(H2_USER);
        dataSource.setPassword(H2_PASS);
    }

    protected Date getFutureDate(int days) {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_YEAR, days);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}

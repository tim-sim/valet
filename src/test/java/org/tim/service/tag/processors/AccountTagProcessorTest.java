package org.tim.service.tag.processors;

import org.junit.Before;
import org.junit.Test;
import org.tim.dao.AccountDAO;

import javax.sql.DataSource;

/**
 * @author tim
 */
public class AccountTagProcessorTest extends BaseDbTest {
    private AccountTagProcessor tagProcessor = new AccountTagProcessor();
    private AccountDAO accountDAO = new AccountDAO();

    @Before
    public void setUp() {
        DataSource dataSource = createDataSource();
        accountDAO.setDataSource(dataSource);
        tagProcessor.setAccountDAO(accountDAO);

    }

    @Test
    public void testParse() throws Exception {
    }
}

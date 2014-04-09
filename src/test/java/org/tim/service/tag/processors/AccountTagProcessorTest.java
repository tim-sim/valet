package org.tim.service.tag.processors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tim.dao.AccountsDAO;
import org.tim.domain.Account;
import org.tim.domain.Note;
import org.tim.domain.Tag;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author tim
 */
public class AccountTagProcessorTest extends BaseDbTest {
    private static final String BANK = "TCS";
    private static final int AMOUNT = 100000;
    private static final int PERCENT = 10;

    private AccountTagProcessor tagProcessor = new AccountTagProcessor();
    private AccountsDAO accountsDAO = new AccountsDAO();

    @Before
    public void setUp() {
        super.setUp();
        accountsDAO.setDataSource(dataSource);
        tagProcessor.setAccountsDAO(accountsDAO);
    }

    @After
    public void tearDown() {
        for (Account account : accountsDAO.getAll()) {
            accountsDAO.delete(account.getId());
        }
    }

    @Test
    public void testParse() throws Exception {
        Date expire = getFutureDate(365);
        Note note = new Note();
        note.getTags().add(new Tag("account"));
        note.setContent(String.format("%s, %d, %d%%, %4$td.%4$tm.%4$tY", BANK, AMOUNT, PERCENT, expire));

        tagProcessor.parse(note);

        List<Account> accounts = accountsDAO.getAll();
        Account account = accounts.get(0);
        assertEquals(BANK, account.getBank());
        assertEquals(AMOUNT, account.getAmount());
        assertEquals(PERCENT, account.getPercent());
        assertEquals(expire, account.getExpire());
    }
}

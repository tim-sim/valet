package org.tim.service.tag;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tim.dao.AccountsDAO;
import org.tim.domain.Account;
import org.tim.domain.Note;
import org.tim.domain.Tag;

import java.util.Arrays;

/**
 * @author tim
 */
public class AccountTagProcessorTest extends BaseDbTest {
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
        Account account = testAccount();
        Note note = testNote(account);

        tagProcessor.parse(note);

        Account savedAccount = accountsDAO.getAll().get(0);
        assertEquals(account, savedAccount);
    }

    private Note testNote(Account account) {
        Note note = new Note();
        note.setTags(Arrays.asList(new Tag("account")));
        note.setContent(account.toString());
        return note;
    }

    private Account testAccount() {
        Account account = new Account();
        account.setBank("some bank");
        account.setAmount(100);
        account.setPercent(10);
        account.setExpire(getDate(365));
        return account;
    }

    private void assertEquals(Account expected, Account actual) {
        Assert.assertEquals(expected.getBank(), actual.getBank());
        Assert.assertEquals(expected.getAmount(), actual.getAmount());
        Assert.assertEquals(expected.getPercent(), actual.getPercent());
        Assert.assertEquals(expected.getExpire(), actual.getExpire());
    }
}

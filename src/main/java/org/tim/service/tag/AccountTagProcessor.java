package org.tim.service.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tim.dao.AccountsDAO;
import org.tim.domain.Account;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.tim.util.FieldUtils.*;

/**
 * @author tim
 */
@Service
public class AccountTagProcessor extends EntityTagProcessor<Account> {
    private static final List<FieldParser> ACCOUNT_SCHEME = Arrays.asList(
            simpleParser(AccountsDAO.FIELD_BANK),
            numberParser(AccountsDAO.FIELD_AMOUNT),
            percentParser(AccountsDAO.FIELD_PERCENT),
            dateParser(AccountsDAO.FIELD_EXPIRE)
    );

    private AccountsDAO accountsDAO;

    @Override
    protected List<FieldParser> getFieldScheme() {
        return ACCOUNT_SCHEME;
    }

    @Override
    protected Account createEntity(Map<String, Object> data) {
        Account account = new Account();
        account.setBank((String) data.get(AccountsDAO.FIELD_BANK));
        account.setAmount((long) data.get(AccountsDAO.FIELD_AMOUNT));
        account.setPercent((int) data.get(AccountsDAO.FIELD_PERCENT));
        account.setExpire((Date) data.get(AccountsDAO.FIELD_EXPIRE));
        return account;
    }

    @Override
    protected AccountsDAO getEntityDAO() {
        return accountsDAO;
    }

    @Override
    public String getTagName() {
        return "account";
    }

    @Autowired
    public void setAccountsDAO(AccountsDAO accountsDAO) {
        this.accountsDAO = accountsDAO;
    }
}

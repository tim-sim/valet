package org.tim.service.tag.processors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tim.dao.AccountDAO;
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
            simpleParser("bank"),
            numberParser("amount"),
            percentParser("percent"),
            dateParser("expire")
    );

    @Autowired
    private AccountDAO accountDAO;

    @Override
    protected String getTagName() {
        return "account";
    }

    @Override
    protected Iterable<FieldParser> getFieldScheme() {
        return ACCOUNT_SCHEME;
    }

    @Override
    protected Account createEntity(Map<String, Object> data) {
        Account account = new Account();
        account.setBank((String) data.get("bank"));
        account.setAmount((long) data.get("amount"));
        account.setPercent((int) data.get("percent"));
        account.setExpire((Date) data.get("expire"));
        return account;
    }

    @Override
    protected AccountDAO getDAO() {
        return accountDAO;
    }

    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }
}

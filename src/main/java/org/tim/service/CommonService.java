package org.tim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tim.dao.AccountsDAO;
import org.tim.dao.TasksDAO;
import org.tim.domain.Account;
import org.tim.domain.Task;

import java.util.List;

/**
 * @author tim.
 */
@Service
public class CommonService {
    @Autowired
    private AccountsDAO accountsDAO;
    @Autowired
    TasksDAO tasksDAO;

    public List<Task> getAllTasks() {
        return tasksDAO.getAll();
    }

    public List<Account> getAllAccounts() {
        return accountsDAO.getAll();
    }
}

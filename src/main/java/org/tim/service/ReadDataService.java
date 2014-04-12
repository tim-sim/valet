package org.tim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tim.dao.*;
import org.tim.domain.*;

import java.util.List;

/**
 * @author tim.
 */
@Service
public class ReadDataService {
    private NotesDAO notesDAO;
    private TagsDAO tagsDAO;
    private AccountsDAO accountsDAO;
    private TasksDAO tasksDAO;
    private EventsDAO eventsDAO;
    private ExpensesDAO expensesDAO;

    @Autowired
    public void setNotesDAO(NotesDAO notesDAO) {
        this.notesDAO = notesDAO;
    }

    @Autowired
    public void setTagsDAO(TagsDAO tagsDAO) {
        this.tagsDAO = tagsDAO;
    }

    @Autowired
    public void setAccountsDAO(AccountsDAO accountsDAO) {
        this.accountsDAO = accountsDAO;
    }

    @Autowired
    public void setTasksDAO(TasksDAO tasksDAO) {
        this.tasksDAO = tasksDAO;
    }

    @Autowired
    public void setEventsDAO(EventsDAO eventsDAO) {
        this.eventsDAO = eventsDAO;
    }

    @Autowired
    public void setExpensesDAO(ExpensesDAO expensesDAO) {
        this.expensesDAO = expensesDAO;
    }

    /*
    * Notes
    */

    public List<Note> getAllNotes() {
        return populateTags(notesDAO.findAll());
    }

    public List<Tag> getAllTags() {
        return tagsDAO.finAll();
    }

    public List<Note> getNotesByTag(long tagId) {
        return populateTags(notesDAO.findByTagId(tagId));
    }

    private List<Note> populateTags(List<Note> notes) {
        for (Note note : notes) {
            note.setTags(tagsDAO.findByNote(note.getId()));
        }
        return notes;
    }

    /*
     * Tasks
     */

    public List<Task> getAllTasks() {
        return tasksDAO.getAll();
    }

    /*
     * Accounts
     */

    public List<Account> getAllAccounts() {
        return accountsDAO.getAll();
    }

    /*
     * Events
     */
    public List<Event> getAllEvents() {
        return eventsDAO.getAll();
    }

    /*
     * Expenses
     */

    public List<Expense> getAllExpenses() {
        return expensesDAO.getAll();
    }
}

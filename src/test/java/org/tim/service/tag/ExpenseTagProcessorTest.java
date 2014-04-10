package org.tim.service.tag;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tim.dao.ExpensesDAO;
import org.tim.domain.Expense;
import org.tim.domain.Note;
import org.tim.domain.Tag;

import java.util.Arrays;

/**
 * @author tim
 */
public class ExpenseTagProcessorTest extends BaseDbTest {
    private ExpensesDAO expensesDAO = new ExpensesDAO();
    private ExpenseTagProcessor tagProcessor = new ExpenseTagProcessor();

    @Before
    public void setUp() {
        super.setUp();
        expensesDAO.setDataSource(dataSource);
        tagProcessor.setExpensesDAO(expensesDAO);
    }

    @After
    public void tearDown() {
        for (Expense expense : expensesDAO.getAll()) {
            expensesDAO.delete(expense.getId());
        }
    }

    @Test
    public void testParse() {
        Expense expense = testExpense();
        Note note = testNote(expense);

        tagProcessor.parse(note);

        Expense savedExpense = expensesDAO.getAll().get(0);
        assertEquals(expense, savedExpense);
    }

    private Note testNote(Expense expense) {
        Note note = new Note();
        note.setContent(expense.toString());
        note.setTags(Arrays.asList(new Tag("expense")));
        note.setCreated(getDate(0));
        return note;
    }

    private Expense testExpense() {
        Expense expense = new Expense();
        expense.setTitle("some expense");
        expense.setCategory("some category");
        expense.setAmount(100);
        expense.setCreated(getDate(0));
        return expense;
    }

    private void assertEquals(Expense expected, Expense actual) {
        Assert.assertEquals(expected.getTitle(), actual.getTitle());
        Assert.assertEquals(expected.getCategory(), actual.getCategory());
        Assert.assertEquals(expected.getAmount(), actual.getAmount());
        Assert.assertEquals(expected.getCreated(), actual.getCreated());
    }
}

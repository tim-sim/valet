package org.tim.service.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tim.dao.EntityDAO;
import org.tim.dao.ExpensesDAO;
import org.tim.domain.Expense;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.tim.dao.ExpensesDAO.*;
import static org.tim.util.FieldUtils.*;

/**
 * @author tim
 */
@Service
public class ExpenseTagProcessor extends EntityTagProcessor<Expense> {
    private static final String TAG_EXPENSE = "expense";
    private static final List<FieldParser> EXPENSE_SCHEME = Arrays.asList(
            simpleParser(FIELD_TITLE),
            simpleParser(FIELD_CATEGORY),
            numberParser(FIELD_AMOUNT),
            dateParser(FIELD_CREATED)
    );
    private ExpensesDAO expensesDAO;

    @Override
    protected List<FieldParser> getFieldScheme() {
        return EXPENSE_SCHEME;
    }

    @Override
    protected Expense createEntity(Map<String, Object> data) {
        Expense expense = new Expense();
        expense.setTitle((String) data.get(FIELD_TITLE));
        expense.setCategory((String) data.get(FIELD_CATEGORY));
        expense.setAmount((long) data.get(FIELD_AMOUNT));
        expense.setCreated((Date) data.get(FIELD_CREATED));
        return expense;
    }

    @Override
    protected EntityDAO<Expense> getEntityDAO() {
        return expensesDAO;
    }

    @Override
    public String getTagName() {
        return TAG_EXPENSE;
    }

    @Autowired
    public void setExpensesDAO(ExpensesDAO expensesDAO) {
        this.expensesDAO = expensesDAO;
    }
}

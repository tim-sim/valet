package org.tim.service.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tim.dao.EntityDAO;
import org.tim.dao.ExpensesDAO;
import org.tim.domain.Expense;
import org.tim.util.FieldUtils;

import java.util.Map;

/**
 * @author TNasibullin
 */
@Service
public class ExpenseTagProcessor extends EntityTagProcessor<Expense> {
    private ExpensesDAO expensesDAO;

    @Override
    protected Iterable<FieldUtils.FieldParser> getFieldScheme() {
        return null;
    }

    @Override
    protected Expense createEntity(Map<String, Object> data) {
        return null;
    }

    @Override
    protected EntityDAO<Expense> getEntityDAO() {
        return expensesDAO;
    }

    @Override
    public String getTagName() {
        return "expense";
    }

    @Autowired
    public void setExpensesDAO(ExpensesDAO expensesDAO) {
        this.expensesDAO = expensesDAO;
    }
}

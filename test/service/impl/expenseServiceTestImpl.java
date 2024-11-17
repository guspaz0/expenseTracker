package service.impl;

import dao.dto.expenseDto;
import entity.Expense;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.ExpenseServiceTest;
import service.expenseCategory;

@ExtendWith(MockitoExtension.class)
public class expenseServiceTestImpl implements ExpenseServiceTest {
    //testear que cada expensa tenga asignada una categoria
    @Mock
    private expenseCategory expenseCategory;


    @Override
    public void addExpense(expenseDto expenseDto) {

    }

    @Override
    public void checkExpirations(int id) {

    }

    @Override
    public void removeExpense(int id) {

    }

    @Override
    public void updateExpense(Expense expense) {

    }
}

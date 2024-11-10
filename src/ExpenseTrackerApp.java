import dao.*;
import dao.dto.*;
import dao.impl.*;

import entity.Category;
import entity.User;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseTrackerApp {
    public static void main(String[] args) {
        try {
            userDao userDao = new userDaoImplH2();
            userDto user = new userDto();
            user.setName("Gustavo Paz");
            user.setEmail("gusti.paz@gmail.com");
            userDao.create(user);
            User user1 = userDao.findByPk(1);
            System.out.println(user1.toString());

//            List<User> userList = userDao.findAll();
//            System.out.println(userList);
//            userDao.delete(1);
            categoryDao categoryDao = new categoryDaoImplH2();
            List<categoryDto> categoryList = new ArrayList<>();
            categoryList.add(new categoryDto("Bienes basicos", "Propductos basicos tangibles como por ejemplo ropa, comida"));
            categoryList.add(new categoryDto("Servicios basicos", "Servicios de basicos por ejemplo agua, electricidad, gas, telefonia, etc."));
            categoryList.forEach(categoryDto -> {
                try {
                    categoryDao.create(categoryDto);
                } catch (Exception e) {
                    System.out.println(String.valueOf(e));
                }
            });
            System.out.println(categoryDao.findAll());

            expenseDao expenseDao = new expenseDaoImplH2();

            List<expenseDto> expenseList = new ArrayList<>();
            expenseList.add(new expenseDto("factura de Electricidad periodo de agosto 2024", Date.valueOf("2024-10-24"),100.00));
            expenseList.add(new expenseDto("factura de Gas periodo de agosto 2024", Date.valueOf("2024-09-28"), 150.00));
            expenseList.add(new expenseDto("factura de Telefonia movil periodo de agosto 2024", Date.valueOf("2024-08-03"),300.0));
            expenseList.add(new expenseDto("factura de comida", Date.valueOf("2024-09-15"),1150.0));
            expenseList.add(new expenseDto("factura de Ropa", Date.valueOf("2024-08-26"),2300.));
            expenseList.add(new expenseDto("factura de Universidad", Date.valueOf("2024-10-12"),5000.));
            expenseList.forEach(expenseDto -> {
                try {
                    expenseDao.create(expenseDto);
                } catch (Exception e) {
                    System.out.println(String.valueOf(e));
                }
            });
            System.out.println(expenseDao.findAll());

            categoryExpensesDao categoryExpensesDao = new categoryExpenseDaoImplH2();
            List<categoryExpensesDto> categoryExpensesDtoList = new ArrayList<>();
            categoryExpensesDtoList.add(new categoryExpensesDto(2,1));
            categoryExpensesDtoList.add(new categoryExpensesDto(2,2));
            categoryExpensesDtoList.add(new categoryExpensesDto(2,3));
            categoryExpensesDtoList.add(new categoryExpensesDto(1,4));
            categoryExpensesDtoList.add(new categoryExpensesDto(1,5));
            categoryExpensesDtoList.add(new categoryExpensesDto(2,6));

            categoryExpensesDtoList.forEach(categoryExpensesDto -> {
                try {
                    categoryExpensesDao.add(categoryExpensesDto);
                } catch (Exception e) {
                    System.out.println(String.valueOf(e));
                }
            });
            userExpensesDao userExpensesDao = new userExpensesDaoImplH2();
            List<userExpensesDto> userExpensesDtoList = new ArrayList<>();
            userExpensesDtoList.add(new userExpensesDto(1,1));
            userExpensesDtoList.add(new userExpensesDto(1,2));
            userExpensesDtoList.add(new userExpensesDto(1,3));
            userExpensesDtoList.add(new userExpensesDto(1,4));
            userExpensesDtoList.add(new userExpensesDto(1,5));
            userExpensesDtoList.add(new userExpensesDto(1,6));
            userExpensesDtoList.forEach(userExpensesDto -> {
                try {
                    userExpensesDao.add(userExpensesDto);
                } catch (Exception e) {
                    System.out.println(String.valueOf(e));
                }
            });
        } catch (Exception e) {
            System.out.println(String.valueOf(e));
        }
    }
}

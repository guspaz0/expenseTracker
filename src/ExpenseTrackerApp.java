import dao.impl.expenseDaoH2;
import entity.User;
import service.expenseService;

public class ExpenseTrackerApp {
    public static void main(String[] args) {
        try {
            User user = new User();
            user.setId(1);

            //expenseService expenseService = new expenseService(new expenseDaoH2(), user);
            expenseService expenseService = new expenseService(new expenseDaoH2());

            System.out.println(expenseService.findAll());

            //expenseExpirationDao expenseExpirationDao = new expenseExpirationDaoImplH2();
            //System.out.println(expenseExpirationDao.findByPk(1).toString());

        } catch (Exception e) {
            System.out.println(String.valueOf(e));
        }
    }
}

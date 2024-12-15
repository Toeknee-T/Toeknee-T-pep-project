package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDao;

    public AccountService() {
        accountDao = new AccountDAO();
    }

    public AccountService(AccountDAO accountDao) {
        this.accountDao = accountDao;
    }

    // Used to add a user to the database.
    // Checks to make sure the password and username are valid by falling
    // within specs.
    public Account addUser(Account acc) {
        if (acc.getPassword().length() < 4) return null;
        if (acc.getUsername().length() == 0) return null;
        Account searchAccount = accountDao.getAccountByUsername(acc.getUsername());
        if (searchAccount == null) {
            return accountDao.addUser(acc);
        }
        return null;
    }

    public Account login(Account acc) {
        Account validatedAccount = accountDao.validateAccount(acc.getUsername(), acc.getPassword());
        return validatedAccount;
    }
}

package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    public void deleteUser(String passport) {
        users.remove(new User(passport, ""));
    }

    public void addAccount(String passport, Account account) {
        User foundUser = findByPassport(passport);
        if (foundUser != null) {
            List<Account> userAcc = users.get(foundUser);
            if (!userAcc.contains(account)) {
                userAcc.add(account);
            }
        }
    }

    public User findByPassport(String passport) {
        User found = null;
        for (User us : users.keySet()) {
            if (us.getPassport().equals(passport)) {
               found = us;
               break;
            }
        }
       return found;
    }

    public Account findByRequisite(String passport, String requisite) {
        Account found = null;
        User foundUser = findByPassport(passport);
        if (foundUser != null) {
            List<Account> userAcc = users.get(foundUser);
            for (Account a : userAcc) {
                if (a.getRequisite().equals(requisite)) {
                    found = a;
                    break;
                }
            }
        }
        return found;
    }

    public boolean transferMoney(String sourcePassport, String sourceRequisite,
                                 String destinationPassport, String destinationRequisite,
                                 double amount) {
        boolean result = false;
        Account source = findByRequisite(sourcePassport, sourceRequisite);
        Account destination = findByRequisite(destinationPassport, destinationRequisite);
        if (source != null && destination != null && source.getBalance() >= amount) {
            source.setBalance(source.getBalance() - amount);
            destination.setBalance(destination.getBalance() + amount);
            result = true;
        }
        return result;
    }

    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}
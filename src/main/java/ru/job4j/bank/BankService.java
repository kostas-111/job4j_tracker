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
           if (findByPassport(passport) != null) {
               users.remove(findByPassport(passport));
           }
    }

    public void addAccount(String passport, Account account) {
        if (findByPassport(passport) != null) {
            List<Account> userAcc = users.get(findByPassport(passport));
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
        if (findByPassport(passport) != null) {
            List<Account> userAcc = users.get(findByPassport(passport));
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
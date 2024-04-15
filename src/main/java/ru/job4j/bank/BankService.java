package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс описывает простейшую модель для банковской системы.
 * В системе можно производить следующие действия.
 * 1. Регистрировать пользователя.
 * 2. Удалять пользователя из системы.
 * 3. Добавлять пользователю банковский счет.
 * 4. Переводить деньги с одного банковского счета на другой счет.
 * У пользователя системы могут быть несколько счетов.
 * @author KONSTANTIN GALKIN
 * @version 1.0
 */
public class BankService {

    /**
     * Пользователи системы с привязанными к ним счетами
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод добавляет пользователя в систему.
     * Чтобы добавить пользователя в систему можно использовать метод Map.put.     *
     * Проверка, что такого пользователя еще нет в системе, осуществлена с использованием
     * метода putIfAbsent.
     * @param user пользователь, объект класса User
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод удаляет пользователя из системы.
     * Для удаления используется метод remove по ключу User
     * @param passport номер паспорта пользователя. По полю паспорт происходит сравнение пользователей
     */
    public void deleteUser(String passport) {
        users.remove(new User(passport, ""));
    }

    /**
     * Метод добавляет новый счет к пользователю.
     * Первоначально пользователя ищем по паспорту с использованием метода findByPassport.
     * После этого мы получим список всех счетов пользователя и добавим новый счет к ним.
     * Счет добавляется, если прохоит валидация на отсутствие такого счета у пользователя.
     * @param passport номер паспорта пользователя.
     * @param account номер счета, который необходимо добавить
     * @link findByPassport
     */
    public void addAccount(String passport, Account account) {
        User foundUser = findByPassport(passport);
        if (foundUser != null) {
            List<Account> userAcc = users.get(foundUser);
            if (!userAcc.contains(account)) {
                userAcc.add(account);
            }
        }
    }

    /**
     * Метод ищет пользователя по номеру паспорта.
     * @param passport номер паспорта пользователя, которого необходимо найти
     * @return возвращает пользователя, найденного по номеру паспорта
     */
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

    /**
     * Метод ищет счет пользователя по реквизитам.
     * Первоначально пользователя ищем по паспорту с использованием метода findByPassport.
     * @param passport номер паспорта пользователя, счет которого необходимо найти
     * @param requisite реквизиты счета, сведения о котором необходимо найти
     * @return возвращает найденный счет пользователя
     * @link findByPassport
     */
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

    /**
     *  Метод предназначен для перечисления денег с одного счёта на другой счёт.
     * @param sourcePassport номер паспорта пользователя, со счета которого перечисляются средства
     * @param sourceRequisite номер счета пользователя, с которого перечисляются средства
     * @param destinationPassport номер паспорта пользователя, на чей счет перечисляются средства
     * @param destinationRequisite номер счета пользователя, на который перечисляются средства
     * @param amount сумма перевода
     * @return возвращает положительный результат при условии, что счет с sourceRequisite найден
     * и на нем достаточно средств для осуществления перевода.
     */
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
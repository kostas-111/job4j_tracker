package ru.job4j.ex;

public class UserStore {
    public static User findUser(User[] users, String login) throws UserNotFoundException {
        User result = null;
        for (int i = 0; i < users.length; i++) {
            if (login.equals(users[i].getUsername())) {
                result = users[i];
            }
        }
        if (result == null) {
            throw new UserNotFoundException("User not found.");
        }
        return result;
    }

    public static boolean validate(User user) throws UserInvalidException {
        if (user.getUsername().length() > 3 && user.isValid()) {
            return true;
        } else {
            throw new UserInvalidException("Invalid user.");
        }
    }

    public static void main(String[] args) {
        User[] users = {
                new User("Petr Arsentev", true)
        };
        try {
            User user = findUser(users, "Petr Arsentev");
            if (validate(user)) {
                System.out.println("This user has an access");
            }
        } catch (UserInvalidException ei) {
            ei.printStackTrace();
        } catch (UserNotFoundException en) {
            en.printStackTrace();
        }
    }
}
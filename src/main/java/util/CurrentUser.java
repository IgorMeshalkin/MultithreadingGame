package util;

import model.User;
import repository.UserRepository;

public class CurrentUser {
    private static User currentUser;
    private static int currentClanGold;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static int getCurrentClanGold() {
        return currentClanGold;
    }

    public static void setCurrentClanGold(int gold) {
        currentClanGold += gold;
    }
}

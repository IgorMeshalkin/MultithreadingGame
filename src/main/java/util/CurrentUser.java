package util;

import model.User;

public class CurrentUser {
    private static User currentUser;
    private static int currentClanGold;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
        currentClanGold = user.getClan().getGold();
    }

    public static int getCurrentClanGold() {
        return currentClanGold;
    }

    public static void addCurrentClanGold(int gold) {
        currentClanGold += gold;
    }
}

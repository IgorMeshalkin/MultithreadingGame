package actions;

import model.Event;
import model.EventType;
import model.User;
import repository.ActionRepository;
import repository.UserRepository;
import util.CurrentUser;

import java.util.List;
import java.util.Random;

public class Battle implements Runnable {
    private final UserRepository userRepository = new UserRepository();
    private final ActionRepository actionRepository = new ActionRepository();
    private int bet;

    public Battle(int bet) {
        this.bet = bet;
    }

    @Override
    public void run() {
        Random random = new Random();
        boolean result = random.nextBoolean();
        User currentUser = CurrentUser.getCurrentUser();
        User enemyUser = choiceOfEnemy();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Event event = new Event();
        event.setUserName(currentUser.getName());
        event.setClanName(currentUser.getClan().getName());
        event.setEnemyClanName(enemyUser.getClan().getName());
        event.setEventType(EventType.BATTLE);
        event.setSuccess(result);

        actionRepository.actionWithEnemy(result ? bet : -bet, enemyUser.getClan().getId(), event);

        if (result) {
            CurrentUser.setCurrentClanGold(bet * 2);
        }
    }

    private User choiceOfEnemy() {
        User result = null;
        List<User> users = userRepository.findAll();
        Random random = new Random();

        do {
            result = users.get(random.nextInt(users.size()));
        } while (result.getClan().equals(CurrentUser.getCurrentUser().getClan()));

        return result;
    }
}

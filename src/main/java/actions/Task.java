package actions;

import model.Event;
import model.EventType;
import model.User;
import repository.ActionRepository;
import util.CurrentUser;

import java.util.Random;

public class Task implements Runnable {
    private final ActionRepository actionRepository = new ActionRepository();
    private int bet;

    public Task(int bet) {
        this.bet = bet;
    }

    @Override
    public void run() {
        Random random = new Random();
        boolean result = random.nextBoolean();
        User currentUser = CurrentUser.getCurrentUser();

        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Event event = new Event();
        event.setUserName(currentUser.getName());
        event.setClanName(currentUser.getClan().getName());
        event.setEventType(EventType.TASK);
        event.setSuccess(result);

        actionRepository.singleAction(result ? bet : -bet, event);

        if (result) {
            CurrentUser.setCurrentClanGold(bet * 2);
        }
    }
}


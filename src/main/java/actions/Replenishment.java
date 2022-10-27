package actions;

import model.Event;
import model.EventType;
import model.User;
import repository.ActionRepository;
import util.CurrentUser;

public class Replenishment implements Runnable {
    private final ActionRepository actionRepository = new ActionRepository();
    private int amount;

    public Replenishment(int amount) {
        this.amount = amount;
    }

    @Override
    public void run() {
        User currentUser = CurrentUser.getCurrentUser();

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Event event = new Event();
        event.setUserName(currentUser.getName());
        event.setClanName(currentUser.getClan().getName());
        event.setEventType(EventType.REPLENISHMENT);
        event.setSuccess(true);

        actionRepository.singleAction(amount, event);

        CurrentUser.setCurrentClanGold(amount);
    }
}

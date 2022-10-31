package controller.rest;

import actions.Battle;
import actions.Replenishment;
import actions.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.EventType;
import repository.UserRepository;
import util.CurrentUser;
import util.RestUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GameRestController extends HttpServlet {
    private static ExecutorService executorService;
    public final UserRepository userRepository = new UserRepository();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init() throws ServletException {
        executorService = Executors.newFixedThreadPool(3);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathVariable = RestUtil.getStringPathVariable(req);

        if (pathVariable != null && pathVariable.equals("stop")) {
            executorService.shutdown();
            try {
                executorService.awaitTermination(5, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                resp.sendRedirect("/events");
            }
        }

        CurrentClanGold clanGold = new CurrentClanGold(CurrentUser.getCurrentClanGold());
        String json = objectMapper.writeValueAsString(clanGold);
        resp.getWriter().write(json);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (executorService.isShutdown()) {
            executorService = Executors.newFixedThreadPool(3);
        }

        NewAction newAction = objectMapper.readValue(RestUtil.readJsonFromRequestBody(req), NewAction.class);

        if (newAction.getType().equals(EventType.BATTLE)) {
            executorService.submit(new Battle(newAction.getBet()));
            CurrentUser.addCurrentClanGold(-newAction.getBet());
        } else if (newAction.getType().equals(EventType.TASK)) {
            executorService.submit(new Task(newAction.getBet()));
            CurrentUser.addCurrentClanGold(-newAction.getBet());
        } else {
            executorService.submit(new Replenishment(newAction.getBet()));
        }
    }
}

class CurrentClanGold {
    private int currentClanGold;

    public CurrentClanGold(int currentClanGold) {
        this.currentClanGold = currentClanGold;
    }

    public int getCurrentClanGold() {
        return currentClanGold;
    }

    public void setCurrentClanGold(int currentClanGold) {
        this.currentClanGold = currentClanGold;
    }
}

class NewAction {
    private EventType type;
    private int bet;

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }
}
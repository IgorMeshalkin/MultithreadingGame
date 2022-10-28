package controller;

import actions.Battle;
import actions.Replenishment;
import actions.Task;
import model.Clan;
import model.EventType;
import model.User;
import repository.ClanRepository;
import repository.EventRepository;
import repository.UserRepository;
import util.CurrentUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ConsoleGameController {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(3);
    public final ClanRepository clanRepository = new ClanRepository();
    public final UserRepository userRepository = new UserRepository();
    public final EventRepository eventRepository = new EventRepository();

    public void startGame() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            newUser(reader);
            action(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void newUser(BufferedReader reader) throws IOException {
        System.out.println("Введи своё имя:");
        String name = reader.readLine();

        System.out.println("Выбери клан:");
        AtomicInteger counter = new AtomicInteger(1);
        List<Clan> clans = clanRepository.findAll();
        clans.forEach(cl -> {
            System.out.println(counter.getAndIncrement() + " - " + cl.getName());
        });
        Clan clan = chooseClan(clans, reader);
//        CurrentUser.addCurrentClanGold(clan.getGold());

        User user = new User();
        user.setName(name);
        user.setClan(clan);
        CurrentUser.setCurrentUser(user);
        userRepository.save(user);
    }

    private Clan chooseClan(List<Clan> clans, BufferedReader reader) {
        Clan clan = null;
        try {
            long clanNumber = Long.parseLong(reader.readLine());
            clan = clans.get((int) (clanNumber - 1));
        } catch (Exception e) {
            System.out.println("Надо ввести номер из списка");
            return chooseClan(clans, reader);
        }
        return clan;
    }

    private void action(BufferedReader reader) {
        System.out.println("Выбери действие:" + "\n"
                + "1 - Начать битву" + "\n"
                + "2 - Выполнить задание" + "\n"
                + "3 - Пополнить казну из своего кармана" + "\n"
                + "4 - Закончить игру" + "\n"
                + "5 - Закончить и посмотреть историю игр"
        );
        int answer = chooseAction(reader);
        switch (answer) {
            case (1):
                newAction(reader, EventType.BATTLE);
                break;
            case (2):
                newAction(reader, EventType.TASK);
                break;
            case (3):
                newAction(reader, EventType.REPLENISHMENT);
                break;
            case (4):
                finishGame();
                break;
            case (5):
                finishGameWithResults();
                break;
        }
    }

    private void newAction(BufferedReader reader, EventType type) {
        int currentClanGold = CurrentUser.getCurrentClanGold();

        if (currentClanGold == 0 && !type.equals(EventType.REPLENISHMENT)) {
            System.out.println("\n" + "Казна твоего клана равна \"0\"" + "\n"
                    + "дождись окончания текущих действий или пополни её за свой счёт" + "\n");
            action(reader);
        }

        System.out.println("Казна твоего клана составляет: " + currentClanGold + " золотых");
        if (type.equals(EventType.BATTLE)) {
            System.out.println("Сколько ты хочешь поставить на бой?");
        } else if (type.equals(EventType.TASK)) {
            System.out.println("Сколько ты хочешь поставить на выполнение задания?");
        } else {
            System.out.println("Сколько ты хочешь внести в казну своего клана?");
        }

        int bet = chooseBet(reader, currentClanGold, type);
        if (!type.equals(EventType.REPLENISHMENT)) {
            CurrentUser.addCurrentClanGold(-bet);
        }

        if (type.equals(EventType.BATTLE)) {
            executorService.submit(new Battle(bet));
            System.out.println("Битва началась, ты можешь не ждать окончания, а выбрать следующее действие" + "\n");
        } else if (type.equals(EventType.TASK)) {
            executorService.submit(new Task(bet));
            System.out.println("Задание началось, ты можешь не ждать окончания, а выбрать следующее действие" + "\n");
        } else {
            executorService.submit(new Replenishment(bet));
            System.out.println("Деньги будут зачислены в ближайшее время," + "\n"
                    + "ты можешь не ждать зачисления, а выбрать следующее действие" + "\n");
        }

        action(reader);
    }

    private void finishGame() {
        executorService.shutdown();
        System.out.println("Игра будет закончена как только завершатся все текущие действия");
    }

    private void finishGameWithResults() {
        executorService.shutdown();
        System.out.println("Игра будет закончена как только завершатся все текущие действия." + "\n"
                + "Сразу после этого ты сможешь посмотреть историю игр." + "\n");
        try {
            executorService.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        eventRepository.findAll().forEach(System.out::println);
    }

    private int chooseAction(BufferedReader reader) {
        int answer = 0;
        try {
            answer = Integer.parseInt(reader.readLine());
            if (answer < 1 || answer > 5) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Надо ввести номер из списка");
            return chooseAction(reader);
        }
        return answer;
    }

    private int chooseBet(BufferedReader reader, int currentClanGold, EventType type) {
        int bet = 0;
        try {
            bet = Integer.parseInt(reader.readLine());
            if (bet > currentClanGold && !type.equals(EventType.REPLENISHMENT)) {
                System.out.println("Остаток казны: " + currentClanGold + ". Ставка не может быть больше" + "\n"
                        + "Сколько ты хочешь поставить?");
                return chooseBet(reader, currentClanGold, type);
            } else if (bet <= 0) {
                System.out.println("Ставка не может быть " + (bet == 0 ? "равна \"0\"" : "отрицательным числом"));
                return chooseBet(reader, currentClanGold, type);
            }
        } catch (Exception e) {
            System.out.println("Надо ввести корректное число");
            return chooseBet(reader, currentClanGold, type);
        }
        return bet;
    }
}



package repository;

import model.Event;
import util.ConnectionManager;
import util.CurrentUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActionRepository {
    private final Connection connection = ConnectionManager.open();

    {
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void singleAction(int bet, Event event) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("BEGIN;"
                             + "UPDATE clans SET clan_gold=(SELECT clan_gold FROM clans WHERE clan_id=?) + ? WHERE clan_id=?;"
                             + "INSERT INTO events (user_name, clan_name, enemy_clan_name, type, success, gold_before, gold_after, date_time)"
                             + "VALUES (?, ?, ?, ?, ?, (SELECT clan_gold FROM clans WHERE clan_id=?) - ?, (SELECT clan_gold FROM clans WHERE clan_id=?), current_timestamp);"
                             + "COMMIT;")) {

            preparedStatement.setLong(1, CurrentUser.getCurrentUser().getClan().getId());
            preparedStatement.setInt(2, bet);
            preparedStatement.setLong(3, CurrentUser.getCurrentUser().getClan().getId());

            preparedStatement.setString(4, event.getUserName());
            preparedStatement.setString(5, event.getClanName());
            preparedStatement.setString(6, event.getEnemyClanName());
            preparedStatement.setString(7, event.getEventType().toString());
            preparedStatement.setBoolean(8, event.isSuccess());
            preparedStatement.setLong(9, CurrentUser.getCurrentUser().getClan().getId());
            preparedStatement.setInt(10, bet);
            preparedStatement.setLong(11, CurrentUser.getCurrentUser().getClan().getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actionWithEnemy(int bet, Long enemyClanId, Event event) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("BEGIN;"
                             + "UPDATE clans SET clan_gold=(SELECT clan_gold FROM clans WHERE clan_id=?) + ? WHERE clan_id=?;"
                             + "UPDATE clans SET clan_gold=(SELECT clan_gold FROM clans WHERE clan_id=?) - ? WHERE clan_id=?;"
                             + "INSERT INTO events (user_name, clan_name, enemy_clan_name, type, success, gold_before, gold_after, date_time)"
                             + "VALUES (?, ?, ?, ?, ?, (SELECT clan_gold FROM clans WHERE clan_id=?) - ?, (SELECT clan_gold FROM clans WHERE clan_id=?), current_timestamp);"
                             + "COMMIT;")) {

            preparedStatement.setLong(1, CurrentUser.getCurrentUser().getClan().getId());
            preparedStatement.setInt(2, bet);
            preparedStatement.setLong(3, CurrentUser.getCurrentUser().getClan().getId());
            preparedStatement.setLong(4, enemyClanId);
            preparedStatement.setInt(5, bet);
            preparedStatement.setLong(6, enemyClanId);

            preparedStatement.setString(7, event.getUserName());
            preparedStatement.setString(8, event.getClanName());
            preparedStatement.setString(9, event.getEnemyClanName());
            preparedStatement.setString(10, event.getEventType().toString());
            preparedStatement.setBoolean(11, event.isSuccess());
            preparedStatement.setLong(12, CurrentUser.getCurrentUser().getClan().getId());
            preparedStatement.setInt(13, bet);
            preparedStatement.setLong(14, CurrentUser.getCurrentUser().getClan().getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

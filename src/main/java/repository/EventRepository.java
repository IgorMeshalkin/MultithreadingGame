package repository;

import model.Event;
import util.ConnectionManager;
import util.ResultSetConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventRepository implements TestTaskRepository<Event> {
    private final Connection connection = ConnectionManager.open();
    private final ResultSetConverter resultSetConverter = new ResultSetConverter();

    @Override
    public List<Event> findAll() {
        List<Event> result = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM events ORDER BY event_id");

            while (resultSet.next()) {
                Event event = resultSetConverter.getEventFromResultSet(resultSet);
                result.add(event);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Event findById(Long id) {
        Event event = null;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM events WHERE event_id=?")) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            event = resultSetConverter.getEventFromResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return event;
    }

    @Override
    public void save(Event event) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO events (user_name, clan_name, enemy_clan_name, type, success, gold_before, gold_after, date_time) VALUES(?, ?, ?, ?, ?, ? ,? ,?)")) {

            preparedStatement.setString(1, event.getUserName());
            preparedStatement.setString(2, event.getClanName());
            preparedStatement.setString(3, event.getEnemyClanName());
            preparedStatement.setString(4, event.getEventType().toString());
            preparedStatement.setBoolean(5, event.isSuccess());
            preparedStatement.setInt(6, event.getClanGoldBefore());
            preparedStatement.setInt(7, event.getClanGoldAfter());
            preparedStatement.setTimestamp(8, Timestamp.valueOf(event.getDateTime()));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Event event) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE events SET user_name =?, clan_name=?, enemy_clan_name =?, type=?, success =?, gold_before=?, gold_after =?, date_time=? WHERE event_id=?")) {

            preparedStatement.setString(1, event.getUserName());
            preparedStatement.setString(2, event.getClanName());
            preparedStatement.setString(3, event.getEnemyClanName());
            preparedStatement.setString(4, event.getEventType().toString());
            preparedStatement.setBoolean(5, event.isSuccess());
            preparedStatement.setInt(6, event.getClanGoldBefore());
            preparedStatement.setInt(7, event.getClanGoldAfter());
            preparedStatement.setTimestamp(8, Timestamp.valueOf(event.getDateTime()));
            preparedStatement.setLong(9, event.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM events WHERE event_id=?")) {

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

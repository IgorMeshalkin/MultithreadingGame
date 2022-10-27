package repository;

import model.Clan;
import model.User;
import util.ConnectionManager;
import util.ResultSetConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserRepository implements TestTaskRepository<User> {
    private static Connection connection = ConnectionManager.open();
    private static ResultSetConverter resultSetConverter = new ResultSetConverter();
    private static Lock lock = new ReentrantLock();

    @Override
    public List<User> findAll() {
        lock.lock();
        try {
            List<User> result = new ArrayList<>();
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users LEFT JOIN clans ON users.clan_id=clans.clan_id ORDER BY user_id");

                while (resultSet.next()) {
                    User user = resultSetConverter.getUserFromResultSet(resultSet);
                    Clan clan = resultSetConverter.getClanFromResultSet(resultSet);
                    user.setClan(clan);
                    result.add(user);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return result;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public User findById(Long id) {
        lock.lock();
        try {
            User user = null;
            Clan clan = null;
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement("SELECT * FROM users LEFT JOIN clans ON users.clan_id=clans.clan_id WHERE user_id=?")) {

                preparedStatement.setLong(1, id);

                ResultSet resultSet = preparedStatement.executeQuery();

                resultSet.next();

                user = resultSetConverter.getUserFromResultSet(resultSet);
                clan = resultSetConverter.getClanFromResultSet(resultSet);
                user.setClan(clan);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return user;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void save(User user) {
        lock.lock();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO users (user_name, clan_id) VALUES(?, ?)")) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setLong(2, user.getClan().getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void update(User user) {
        lock.lock();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE users SET user_name=?, clan_id=? WHERE user_id=?")) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setLong(2, user.getClan().getId());
            preparedStatement.setLong(3, user.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void delete(Long id) {
        lock.lock();
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE user_id=?")) {

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

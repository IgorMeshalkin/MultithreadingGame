package repository;

import model.Clan;
import util.ConnectionManager;
import util.ResultSetConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClanRepository implements TestTaskRepository<Clan> {
    private final Connection connection = ConnectionManager.open();
    private final ResultSetConverter resultSetConverter = new ResultSetConverter();

    {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Clan> findAll() {
        List<Clan> result = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM clans ORDER BY clan_id");

            while (resultSet.next()) {
                Clan clan = resultSetConverter.getClanFromResultSet(resultSet);
                result.add(clan);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Clan findById(Long id) {
        Clan clan = null;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM clans WHERE clan_id=?")) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            clan = resultSetConverter.getClanFromResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clan;
    }

    @Override
    public void save(Clan clan) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO clans (clan_name, clan_gold) VALUES(?, ?)")) {

            preparedStatement.setString(1, clan.getName());
            preparedStatement.setInt(2, clan.getGold());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Clan clan) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE clans SET clan_name =?, clan_gold=? WHERE clan_id=?")) {

            preparedStatement.setString(1, clan.getName());
            preparedStatement.setInt(2, clan.getGold());
            preparedStatement.setLong(3, clan.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM clans WHERE clan_id=?")) {

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

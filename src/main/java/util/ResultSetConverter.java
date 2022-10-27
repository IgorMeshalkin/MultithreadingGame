package util;

import model.Clan;
import model.Event;
import model.EventType;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetConverter {

    public User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getLong("user_id"));
        user.setName(resultSet.getString("user_name"));

        return user;
    }

    public Clan getClanFromResultSet(ResultSet resultSet) throws SQLException {
        Clan clan = new Clan();

        clan.setId(resultSet.getLong("clan_id"));
        clan.setName(resultSet.getString("clan_name"));
        clan.setGold(resultSet.getInt("clan_gold"));

        return clan;
    }

    public Event getEventFromResultSet(ResultSet resultSet) throws SQLException {
        Event event = new Event();

        event.setId(resultSet.getLong("event_id"));
        event.setUserName(resultSet.getString("user_name"));
        event.setClanName(resultSet.getString("clan_name"));
        event.setEnemyClanName(resultSet.getString("enemy_clan_name"));
        event.setEventType(EventType.valueOf(resultSet.getString("type")));
        event.setSuccess(resultSet.getBoolean("success"));
        event.setClanGoldBefore(resultSet.getInt("gold_before"));
        event.setClanGoldAfter(resultSet.getInt("gold_after"));
        event.setDateTime(resultSet.getTimestamp("date_time").toLocalDateTime());

        return event;
    }
}

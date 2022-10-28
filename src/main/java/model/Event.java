package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event extends AbstractBaseModel {
    private String userName;
    private String clanName;
    private String enemyClanName;
    private EventType eventType;
    private boolean success;
    private int clanGoldBefore;
    private int clanGoldAfter;
    private LocalDateTime dateTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClanName() {
        return clanName;
    }

    public void setClanName(String clanName) {
        this.clanName = clanName;
    }

    public String getEnemyClanName() {
        return enemyClanName;
    }

    public void setEnemyClanName(String enemyClanName) {
        this.enemyClanName = enemyClanName;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getClanGoldBefore() {
        return clanGoldBefore;
    }

    public void setClanGoldBefore(int clanGoldBefore) {
        this.clanGoldBefore = clanGoldBefore;
    }

    public int getClanGoldAfter() {
        return clanGoldAfter;
    }

    public void setClanGoldAfter(int clanGoldAfter) {
        this.clanGoldAfter = clanGoldAfter;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Event{" +
                "userName='" + userName + '\'' +
                ", clanName='" + clanName + '\'' +
                ", enemyClanName='" + enemyClanName + '\'' +
                ", eventType=" + eventType +
                ", success=" + success +
                ", clanGoldBefore=" + clanGoldBefore +
                ", clanGoldAfter=" + clanGoldAfter +
                ", dateTime=" + dateTime +
                '}';
    }
}

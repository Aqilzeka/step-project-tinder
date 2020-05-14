package entity;

import java.util.Objects;

public class Message {

    protected int id;
    protected int userTo;
    protected int userFrom;
    protected int localId;
    protected String message;
    protected String dateTime;

    public Message(int userTo, int userFrom, int localId, String message, String dateTime) {
        this.userTo = userTo;
        this.userFrom = userFrom;
        this.localId = localId;
        this.message = message;
        this.dateTime = dateTime;
    }

    public Message(int id, int userTo, int userFrom, int localId, String message, String dateTime) {
        this(userTo,userFrom,localId,message,dateTime);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getUserTo() {
        return userTo;
    }

    public int getUserFrom() {
        return userFrom;
    }

    public int getLocalId() {
        return localId;
    }

    public String getMessage() {
        return message;
    }

    public String getDateTime() {
        return dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return id == message1.id &&
                userTo == message1.userTo &&
                userFrom == message1.userFrom &&
                localId == message1.localId &&
                Objects.equals(message, message1.message) &&
                Objects.equals(dateTime, message1.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userTo, userFrom, localId, message, dateTime);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", userTo=" + userTo +
                ", userFrom=" + userFrom +
                ", localId=" + localId +
                ", message='" + message + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}

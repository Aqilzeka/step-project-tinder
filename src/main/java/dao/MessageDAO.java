package dao;

import entity.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MessageDAO implements DAO<Message> {

    private static final String MESSAGES = "SELECT * FROM message;";
    private static final String DELETE  = "DELETE FROM message;";
    private static final String INSERT  = "INSERT INTO message " +
            "(user_from, user_to, message, localId, datetime) values (?,?,?,?,?);";


    private List<Message> messages;

    public MessageDAO() {
        this.messages = new ArrayList<>();
        read();
    }

    @Override
    public List<Integer> getAllId() {
        return messages
                .stream()
                .map(Message::getId)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public List<Message> getDatabase() {
        return messages;
    }

    @Override
    public Stream<Message> stream() {
        return messages.stream();
    }

    @Override
    public Integer size() {
        return messages.size();
    }

    @Override
    public Message get(int id) {
        return stream()
                .filter(message -> message.getId() == id)
                .collect(Collectors.toList())
                .get(0);
    }

    @Override
    public void read() {
        messages = new LinkedList<>();
        try {

            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(MESSAGES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                messages.add(
                        new Message(
                                resultSet.getInt("id"),
                                resultSet.getInt("user_to"),
                                resultSet.getInt("user_from"),
                                resultSet.getInt("localId"),
                                resultSet.getString("message"),
                                resultSet.getString("dateTime")
                        )
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Can't read messages", e);
        }
    }

    @Override
    public void clear() {
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.executeUpdate();
            messages = new LinkedList<>();
        } catch (SQLException e) {
            throw new RuntimeException("Can't deleted from message", e);
        }
    }

    @Override
    public void add(Message message) {
        try {

            Connection connection = DBConnection.getConnection();
            PreparedStatement insertToMessage = connection.prepareStatement(INSERT);
            insertToMessage.setInt(1, message.getUserFrom());
            insertToMessage.setInt(2, message.getUserTo());
            insertToMessage.setString(3, message.getMessage());
            insertToMessage.setInt(4, message.getLocalId());
            insertToMessage.setString(5, message.getDateTime());
            insertToMessage.executeUpdate();
            messages.add(message);
        } catch (SQLException e) {
            throw new RuntimeException("Can't added to message", e);
        }
    }

    @Override
    public Iterator<Message> iterator() {
        return messages.iterator();
    }
}

package org.tinder.project.dao;

import lombok.extern.log4j.Log4j2;
import org.tinder.project.db.DBConnection;
import org.tinder.project.entity.Message;

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

@Log4j2
public class MessageDAO implements DAO<Message> {

    private static final String MESSAGES = "SELECT * FROM \"messages\";";
    private static final String INSERT = "INSERT INTO \"messages\" " +
            "(user_to, user_from, local_id, my_message, date_time) values (?,?,?,?,?);";


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
                                resultSet.getInt("local_id"),
                                resultSet.getString("my_message"),
                                resultSet.getString("date_time")
                        )
                );
            }

        } catch (SQLException e) {
            log.error("Can't read messages", e);
        }
    }


    @Override
    public void add(Message message) {
        try {

            Connection connection = DBConnection.getConnection();
            PreparedStatement insertToMessage = connection.prepareStatement(INSERT);
            System.out.println("getUserFrom  " + message.getUserFrom());
            System.out.println("getUserTo  " + message.getUserTo());
            System.out.println("getId  " + message.getId());
            System.out.println("getDateTime  " + message.getDateTime());
            System.out.println("getLocalId  " + message.getLocalId());
            System.out.println("getMessage  " + message.getMessage());

            insertToMessage.setInt(1,message.getUserTo());
            insertToMessage.setInt(2, message.getUserFrom());
            insertToMessage.setInt(3,message.getLocalId());
            insertToMessage.setString(4, message.getMessage());
            insertToMessage.setString(5, message.getDateTime());
            insertToMessage.executeUpdate();
            messages.add(message);
        } catch (SQLException e) {
            log.error("Can't added to message", e);
        }
    }

    @Override
    public Iterator<Message> iterator() {
        return messages.iterator();
    }

}
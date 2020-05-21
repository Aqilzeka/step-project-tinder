package org.tinder.project.service;

import lombok.extern.log4j.Log4j2;
import org.tinder.project.dao.MessageDAO;
import org.tinder.project.dao.UserDAO;
import org.tinder.project.entity.Message;
import org.tinder.project.entity.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class MessageService {
    protected MessageDAO messages;
    protected UserDAO users;

    public MessageService() {
        users = new UserDAO();
        messages = new MessageDAO();
    }

    private int getLastLocalId(int sender, int receiver) {
        return messages.stream()
                .filter(message -> (
                                (message.getUserFrom() == sender   &&
                                message.getUserTo() == receiver)   ||
                                (message.getUserFrom() == receiver &&
                                message.getUserTo() == sender)
                ))
                .map(Message::getLocalId)
                .max(Integer::compareTo)
                .orElse(0);
    }

    private String convertToSenderMessage(Message message) {
        return "<li class=\"send-msg float-right mb-2\"><p class=\"pt-1 pb-1 pl-2 pr-2 m-0 rounded\">" +
                message.getMessage() +
                "</p><span class=\"receive-msg-time\">" + message.getDateTime() + "</li><br>";
    }

    private String convertToReceiverMessage(Message message, String profileURL) {
        return "<li class=\"receive-msg float-left mb-2\"><div class=\"sender-img\"> <img src=\""
                + profileURL +
                "\" class=\"float-left\"></div><div class=\"receive-msg-desc float-left ml-2\"><p class=\"bg-white m-0 pt-1 pb-1 pl-2 pr-2 rounded\">"
                + message.getMessage() +
                "</p><span class=\"receive-msg-time\">" + message.getDateTime() + "</li>";
    }

    public User getUser(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(new User("",""));
    }

    public void write(int sender, int receiver, String message){
        if (!message.isEmpty()){
            int lastLocalId = getLastLocalId(sender,receiver);
            log.info(lastLocalId);
            messages.add(new Message(receiver,sender,lastLocalId + 1,message,
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm MM-dd-yyyy"))));
        }
    }

    public List<Message> getMessage(int sender, int receiver){
        return messages.stream()
                .filter(message -> ((
                                message.getUserFrom() == sender &&
                                message.getUserTo() == receiver) || (
                                message.getUserFrom() == receiver &&
                                message.getUserTo() == sender  )))
                .sorted(Comparator.comparingInt(Message::getLocalId))
                .collect(Collectors.toList());
    }

    public List<String> getFormattedMessages(int sender, int receiver){
        this.messages.read();
        User receiverUser = users.get(receiver);
        List<Message> messages = getMessage(sender, receiver);
        return messages.stream()
                .map(message -> (message.getUserTo() == receiver && message.getUserFrom() == sender)
                        ? convertToSenderMessage(message)
                        : convertToReceiverMessage(message,receiverUser.getImgURL()))
                .collect(Collectors.toList());
    }

}

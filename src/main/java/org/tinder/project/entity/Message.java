package org.tinder.project.entity;

import lombok.*;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
@Data
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

}

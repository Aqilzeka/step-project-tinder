package org.tinder.project.entity;

import lombok.*;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Like {
    protected int id;
    protected int user_from;
    protected int user_to;


    public Like(int user_from, int user_to) {
        this.user_from = user_from;
        this.user_to = user_to;
    }

}

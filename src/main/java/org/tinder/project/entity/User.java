package org.tinder.project.entity;

import lombok.*;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data

public class User {

    protected int id;
    protected String name;
    protected String email;
    protected String password;
    protected String gender;
    protected String title;
    protected String imgURL;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String gender, String title, String image) {
        this.name = name;
        this.gender = gender;
        this.title = title;
        this.imgURL = image;
    }

    @NonNull
    public boolean checkEqual(User user) {
        return email.equals(user.email) &&
                password.equals(user.password);
    }

}

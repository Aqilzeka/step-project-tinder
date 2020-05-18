package entity;

import java.util.Objects;
import java.util.UUID;

public class User {

    protected int id;
    protected String name;
    protected String email;
    protected String password;
    protected String gender;
    protected String title;
    protected String imgURL;


    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(int id, String name, String email, String password, String gender, String title, String image) {
        this(email,password);
        this.name = name;
        this.gender = gender;
        this.title = title;
        this.imgURL = image;
        this.id = id;
    }


    public User(String name, String gender, String title, String image) {
        this.name = name;
        this.gender = gender;
        this.title = title;
        this.imgURL = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getTitle() {
        return title;
    }

    public String getImgURL() {
        return imgURL;
    }

    public boolean checkEqual(User user) {
        return email.equals(user.getEmail()) &&
                password.equals(user.getPassword());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(gender, user.gender) &&
                Objects.equals(title, user.title) &&
                Objects.equals(imgURL, user.imgURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, password, gender, title, imgURL);
    }
}

package entity;

public class User {

    protected String name;
    protected String email;
    protected String password;
    protected String gender;
    protected String title;
    protected String photo;


    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password, String gender, String title, String image) {
        this(email,password);
        this.name = name;
        this.gender = gender;
        this.title = title;
        this.photo = image;
    }


    public User(String name, String gender, String title, String image) {
        this.name = name;
        this.gender = gender;
        this.title = title;
        this.photo = image;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}

package entity;

import java.util.Objects;

public class Like {
    protected int id;
    protected int user_from;
    protected int user_to;

    public Like(int user_from, int user_to) {
        this.user_from = user_from;
        this.user_to = user_to;
    }

    public Like(int id, int user_from, int user_to) {
        this(user_from,user_to);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getUser_from() {
        return user_from;
    }

    public int getUser_to() {
        return user_to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Like like = (Like) o;
        return id == like.id &&
                user_from == like.user_from &&
                user_to == like.user_to;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_from, user_to);
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", user_from=" + user_from +
                ", user_to=" + user_to +
                '}';
    }
}

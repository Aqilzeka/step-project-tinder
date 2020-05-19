package org.tinder.project.service;

import org.tinder.project.dao.LikeDAO;
import org.tinder.project.dao.UserDAO;
import org.tinder.project.entity.Like;
import org.tinder.project.entity.User;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LikeService {

    protected UserDAO users;
    protected LikeDAO likes;
    protected List<Integer> userIds;
    protected boolean liked;
    protected int id;

    public int getLocalId() {
        return id;
    }

    public LikeService() {
        liked = false;
        likes = new LikeDAO();
        users = new UserDAO();
        userIds = users.getAllId();
    }

    public void like(int user_to) {
        liked = true;
        Like like = new Like(id, user_to);
        if (!likes.getDatabase().contains(like)) likes.add(like);
    }

    public User getFirst() {
        return getUserNotMe()
                .collect(Collectors.toList())
                .get(0);
    }

    private Stream<User> getUserNotMe() {
        return users.stream()
                .filter(user -> user.getId() != id);
    }

    public boolean isLast() {
        return userIds.isEmpty();
    }

    public boolean isLiked() {
        return liked;
    }

    public User getNext(int user_to) throws IndexOutOfBoundsException {
        userIds.remove(Integer.valueOf(user_to));

        if (!isLast()) return users.get(userIds.get(0));
        else if (isLiked()) throw new IndexOutOfBoundsException();
        else userIds = users.getAllId();
        return null;
    }

    public void setLocalId(int id) {
        this.id = id;
    }
}

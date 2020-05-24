package org.tinder.project.service;

import org.tinder.project.dao.LikeDAO;
import org.tinder.project.dao.UserDAO;
import org.tinder.project.entity.Like;
import org.tinder.project.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class LikedService {

    protected LikeDAO likes;
    protected UserDAO users;

    public LikedService() {
        likes = new LikeDAO();
        users = new UserDAO();
    }

    public List<User> getLikedUsers(int localId) {
        likes = new LikeDAO();
        users = new UserDAO();
        List<Like> likedUsersIds = getLikedUserIds(localId);
        return users.stream()
                .filter(user -> likedUsersIds.contains(new Like(localId, user.getId())))
                .collect(Collectors.toList());
    }

    private List<Like> getLikedUserIds(int localId) {
        likes.read();
        return likes.stream()
                .filter(like -> like.getUser_from() == localId)
                .collect(Collectors.toList());
    }
}

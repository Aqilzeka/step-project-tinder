package service;

import dao.LikeDAO;
import dao.UserDAO;
import entity.Like;
import entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class LikedService {

    protected LikeDAO likes;
    protected UserDAO users;

    public LikedService() {
        this.likes = new LikeDAO();
        this.users = new UserDAO();
    }

    public List<User> getLikedUsers(int localId) {
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

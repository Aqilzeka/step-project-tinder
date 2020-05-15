package dao;

import entity.Like;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LikeDAO implements DAO<Like> {


    private static final String LIKES = "SELECT * FROM likes;";
    private static final String DELETE = "DELETE FROM likes;";
    private static final String INSERT = "INSERT INTO likes " +
            "(user_from, user_to) values (?,?)";



    private List<Like> likes;

    public LikeDAO() {
        this.likes = new LinkedList<>();
        read();
    }

    @Override
    public List<Integer> getAllId() {
        return
                 likes
                .stream()
                .map(Like::getId)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public List<Like> getDatabase() {
        return likes;
    }

    @Override
    public Stream<Like> stream() {
        return likes.stream();
    }

    @Override
    public Integer size() {
        return likes.size();
    }

    @Override
    public Like get(int id) {
        return
                 stream()
                .filter(like -> like.getId() == id)
                .collect(Collectors.toList()).get(0);
    }

    @Override
    public void read() {
        likes = new LinkedList<>();
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(LIKES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                likes.add(
                        new Like(
                                resultSet.getInt("id"),
                                resultSet.getInt("user_from"),
                                resultSet.getInt("user_to")
                        )
                );
            }
        } catch (SQLException e){
            throw new RuntimeException("Can't read likes", e);
        }

    }

    @Override
    public void clear() {
        try{
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.executeUpdate();
            likes = new LinkedList<>();
        } catch (SQLException e){
            throw new RuntimeException("Can't deleted from likes");
        }

    }

    @Override
    public void add(Like like) {
        try{
            Connection connection = DBConnection.getConnection();
            PreparedStatement insertLikes = connection.prepareStatement(INSERT);
            insertLikes.setInt(1,like.getUser_from());
            insertLikes.setInt(2,like.getUser_to());
            insertLikes.executeUpdate();
            likes.add(like);
        }catch (SQLException e){
            throw new RuntimeException("Can't inserted to likes", e);
        }
    }

    @Override
    public Iterator<Like> iterator() {
        return likes.iterator();
    }
}

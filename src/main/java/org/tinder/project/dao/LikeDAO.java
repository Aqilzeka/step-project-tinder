package org.tinder.project.dao;

import lombok.extern.log4j.Log4j2;
import org.tinder.project.db.DBConnection;
import org.tinder.project.entity.Like;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
public class LikeDAO implements DAO<Like> {


    private static final String LIKES = "SELECT * FROM likes;";
    private static final String INSERT = "INSERT INTO likes " +
            "(user_from, user_to) values (?,?)";

    private List<Like> likes;

    public LikeDAO() {
        likes = new LinkedList<>();
        read();
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
                                resultSet.getInt("user_from"),
                                resultSet.getInt("user_to")
                        )
                );
            }
        } catch (SQLException e){
            log.error("Can't read likes", e);
        }

    }

    @Override
    public List<Integer> getAllId() {

        return likes.stream().map(Like::getId).collect(Collectors.toCollection(LinkedList::new));
//       List<Integer> result = new LinkedList<>();
//        try {
//            Connection connection = DBConnection.getConnection();
//            PreparedStatement getIds = connection.prepareStatement(LIKES);
//            ResultSet resultSet = getIds.executeQuery();
//            while (resultSet.next()){
//                result.add(resultSet.getInt("id"));
//            }
//        }  catch (SQLException e) {
//            log.error("Didn't read " + e);
//        }
//        return result;
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
        } catch (SQLException e){
            log.error("Can't inserted to likes", e);
        }
    }

    @Override
    public Iterator<Like> iterator() {
        return likes.iterator();
    }
}

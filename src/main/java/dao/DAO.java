package dao;

import java.util.HashMap;
import java.util.List;

public interface DAO<T> {

    void insertEmailAndPwd(T t);
    void insertNameGenderTitlePhoto(T t);
    T select(String element);
    List<String> getPhotos();
    List<String> getNames();
    List<String> getEmails();
}

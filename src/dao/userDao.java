package dao;

import dao.dto.userDto;
import entity.User;
import java.util.List;

public interface userDao {

    List<User> getAllUsers();
    User findByPk(int id);

    void registerUser(userDto userDto);
    void updateUser(User persona);
    void deleteUser(int id);

}

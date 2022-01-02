package dao.custom;

import dao.SuperDAO;
import entity.User;

public interface UserDAO extends SuperDAO<User,String> {
    User searchUser(String username, String password) throws Exception;
}

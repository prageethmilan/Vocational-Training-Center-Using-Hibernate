package bo.custom.impl;

import bo.custom.LogInFormBO;
import dao.DAOFactory;
import dao.custom.UserDAO;
import dto.UserDTO;
import entity.User;

import java.util.ArrayList;

public class LogInFormBOImpl implements LogInFormBO {
    UserDAO userDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public ArrayList<UserDTO> getAllUsers() throws Exception {
        ArrayList<User> users = userDAO.getAll();
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            userDTOS.add(new UserDTO(user.getFirstName(),user.getLastName(),user.getUserName(),user.getPassword()));
        }
        return userDTOS;
    }

    @Override
    public UserDTO getUser(String username, String password) throws Exception {
        User user = userDAO.searchUser(username,password);
        if(user != null) {
            UserDTO dto = new UserDTO(user.getFirstName(), user.getLastName(), user.getUserName(), user.getPassword());
            return dto;
        }
        return null;
    }
}

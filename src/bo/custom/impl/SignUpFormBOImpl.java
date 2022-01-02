package bo.custom.impl;

import bo.custom.SignUpFormBO;
import dao.DAOFactory;
import dao.custom.UserDAO;
import dto.UserDTO;
import entity.User;

public class SignUpFormBOImpl implements SignUpFormBO {
    UserDAO userDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean SaveUser(UserDTO dto) throws Exception {
        User user = new User(dto.getFirstName(), dto.getLastName(), dto.getUserName(), dto.getPassword());
        return userDAO.add(user);
    }
}

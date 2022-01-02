package bo.custom;

import bo.SuperBO;
import dto.UserDTO;

import java.util.ArrayList;

public interface LogInFormBO extends SuperBO {
    ArrayList<UserDTO> getAllUsers() throws Exception;

    UserDTO getUser(String username, String password) throws Exception;
}

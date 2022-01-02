package bo.custom;

import bo.SuperBO;
import dto.UserDTO;

public interface SignUpFormBO extends SuperBO {
    boolean SaveUser(UserDTO userDTO) throws Exception;
}

package lk.ijse.bookshop.dao.custom;

import lk.ijse.bookshop.dao.CrudDAO;
import lk.ijse.bookshop.dto.UserDTO;
import lk.ijse.bookshop.entity.User;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User>{
    String getUserName() throws SQLException, ClassNotFoundException;
    boolean passwordReset(String username, String password) throws SQLException, ClassNotFoundException;
    boolean checkUsername(String Username) throws SQLException, ClassNotFoundException;
    UserDTO getLoginData(String username, String password) throws SQLException, ClassNotFoundException;
    boolean userDetails(User userDTO) throws SQLException, ClassNotFoundException;
}

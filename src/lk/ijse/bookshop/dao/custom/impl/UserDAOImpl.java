package lk.ijse.bookshop.dao.custom.impl;

import lk.ijse.bookshop.dao.SQLUtil;
import lk.ijse.bookshop.dao.custom.UserDAO;
import lk.ijse.bookshop.dto.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAOImpl implements UserDAO {
    public static String role;

    public String getUserName() throws SQLException, ClassNotFoundException {
        String sql = "SELECT Username FROM user WHERE Role='Admin'";
        ResultSet resultSet = SQLUtil.execute(sql);
        if (resultSet.next()) {
            return resultSet.getString(1);

        }
        return null;
    }

    public UserDTO getLoginData(String username, String password) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM user WHERE Username=? AND Password=?";
        ResultSet resultSet = SQLUtil.execute(sql, username, password);
        if (resultSet.next()) {

            return new UserDTO(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
        }
        return null;
    }

    public boolean checkUsername(String Username) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM user WHERE Username=?";
        ResultSet resultSet = SQLUtil.execute(sql, Username);
        if (resultSet.next()) {
            role = resultSet.getString(3);
            return true;
        }
        return false;
    }

    public boolean passwordReset(String username, String password) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE user SET Password=? WHERE Username=?";
        return SQLUtil.execute(sql, password, username);
    }
}

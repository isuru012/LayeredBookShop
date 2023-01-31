package lk.ijse.bookshop.dao.custom.impl;

import lk.ijse.bookshop.dao.SQLUtil;
import lk.ijse.bookshop.dao.custom.UserDAO;
import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.dto.OrderDetailDTO;
import lk.ijse.bookshop.dto.UserDTO;
import lk.ijse.bookshop.entity.CusOrder;
import lk.ijse.bookshop.entity.CusOrderDetails;
import lk.ijse.bookshop.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


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

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean insert(User customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(User customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public User search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveO(CusOrder cusOrder) throws SQLException, ClassNotFoundException {
        return false;
    }


    public boolean userDetails(User userDTO) throws SQLException, ClassNotFoundException {
        PreparedStatement statement= DBConnection.getDBConnection().getConnection().
                prepareStatement("INSERT INTO user VALUES(?,?,?) ");
        statement.setObject(1, userDTO.getUserName());
        statement.setObject(2, userDTO.getPassword());
        statement.setObject(3, userDTO.getRole());

        return statement.executeUpdate()>0;
    }
}

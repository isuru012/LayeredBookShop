package lk.ijse.bookshop.model;

import lk.ijse.bookshop.to.User;
import lk.ijse.bookshop.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCreationModel {
    public static String role;
    public static boolean userAllDetailSave(User user) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO user VALUES(?,?,?)";
        return CrudUtil.execute(sql,user.getUserName(),user.getPassword(),user.getRole());
    }
    public static User getLoginData(String username,String password) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM user WHERE Username=? AND Password=?";
        ResultSet resultSet=CrudUtil.execute(sql,username,password);
        if (resultSet.next()){

            return new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3) );
        }
        return null;
    }
    public static boolean checkUsername(String Username) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM user WHERE Username=?";
        ResultSet resultSet=CrudUtil.execute(sql,Username);
        if (resultSet.next()){
            role=resultSet.getString(3);
            return true;
        }
        return false;
    }
    public static boolean passwordReset(String username,String password) throws SQLException, ClassNotFoundException {
        String sql="UPDATE user SET Password=? WHERE Username=?";
        return CrudUtil.execute(sql,password,username);
    }
    public static String getEmployeeId(String Username) throws SQLException, ClassNotFoundException {
        String sql="SELECT EmployeeId FROM employee WHERE Username=? ";
       ResultSet resultSet=CrudUtil.execute(sql,Username);
       if (resultSet.next()){
           return resultSet.getString(1);
       }
       return null;
    }
    public static String getEmployeeName(String id) throws SQLException, ClassNotFoundException {
        String sql="SELECT Name FROM employee WHERE EmployeeId=?";
        ResultSet resultSet= CrudUtil.execute(sql,id);
        if (resultSet.next()){

            return resultSet.getString(1);
        }

        return null;
    }
}

package lk.ijse.bookshop.model;

import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.dto.EmployeeDTO;
import lk.ijse.bookshop.dto.UserDTO;
import lk.ijse.bookshop.dao.SQLUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCreationModel {

    public static boolean userAllDetailSave(UserDTO userDTO, EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDBConnection().getConnection().setAutoCommit(false);
            PreparedStatement statement=DBConnection.getDBConnection().getConnection().prepareStatement("INSERT " +
                    "INTO user VALUES(?,?,?) ");
            statement.setObject(1, userDTO.getUserName());
            statement.setObject(2, userDTO.getPassword());
            statement.setObject(3, userDTO.getRole());


            boolean isAddedOrder=statement.executeUpdate()>0;
            if (isAddedOrder) {
                boolean updateEmployeeTable = EmployeeModel.updateTable(employeeDTO);
                if (updateEmployeeTable) {
                    DBConnection.getDBConnection().getConnection().commit();
                    return true;

                }
            }
            DBConnection.getDBConnection().getConnection().rollback();
            return false;

        }finally {
            DBConnection.getDBConnection().getConnection().setAutoCommit(true);
        }

    }



    /*public static UserDTO getLoginData(String username, String password) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM user WHERE Username=? AND Password=?";
        ResultSet resultSet= SQLUtil.execute(sql,username,password);
        if (resultSet.next()){

            return new UserDTO(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3) );
        }
        return null;
    }
    public static boolean checkUsername(String Username) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM user WHERE Username=?";
        ResultSet resultSet= SQLUtil.execute(sql,Username);
        if (resultSet.next()){
            role=resultSet.getString(3);
            return true;
        }
        return false;
    }
    public static boolean passwordReset(String username,String password) throws SQLException, ClassNotFoundException {
        String sql="UPDATE user SET Password=? WHERE Username=?";
        return SQLUtil.execute(sql,password,username);
    }*/
    /*public static String getEmployeeId(String Username) throws SQLException, ClassNotFoundException {
        String sql="SELECT EmployeeId FROM employee WHERE Username=? ";
       ResultSet resultSet= SQLUtil.execute(sql,Username);
       if (resultSet.next()){
           return resultSet.getString(1);
       }
       return null;
    }
    public static String getEmployeeName(String id) throws SQLException, ClassNotFoundException {
        String sql="SELECT Name FROM employee WHERE EmployeeId=?";
        ResultSet resultSet= SQLUtil.execute(sql,id);
        if (resultSet.next()){

            return resultSet.getString(1);
        }

        return null;
    }*/
}

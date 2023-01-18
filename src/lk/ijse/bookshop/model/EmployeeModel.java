package lk.ijse.bookshop.model;

import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.dto.EmployeeDTO;
import lk.ijse.bookshop.dao.SQLUtil;
import lk.ijse.bookshop.view.tm.EmployeeTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeModel {
    public static ArrayList getAllDetails() throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM employee";
        ResultSet resultSet= SQLUtil.execute(sql);
        ArrayList arrayList=new ArrayList();
        while (resultSet.next()){
            arrayList.add(new EmployeeTm(resultSet.getString(1),resultSet.getString(2)
                    ,resultSet.getString(3),resultSet.getInt(4),resultSet.getDouble(5)));

        }
        return arrayList;
    }

    public static boolean updateSalary(String employeeId, double salary) throws SQLException, ClassNotFoundException {
        String sql="UPDATE employee SET Salary=? WHERE EmployeeId=?";
        return SQLUtil.execute(sql,salary,employeeId);
    }

    public static boolean deleteEmployee(String employeeId) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM employee WHERE EmployeeId=? ";
        return SQLUtil.execute(sql,employeeId);
    }


    public static String currentEmployeeId() throws SQLException, ClassNotFoundException {
        String sql="SELECT EmployeeId FROM employee ORDER BY EmployeeId DESC LIMIT 1";
        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    public static boolean updateTable(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DBConnection.getDBConnection().getConnection().prepareStatement("INSERT INTO employee values(?,?,?,?,?,?)");

        statement.setObject(1, employeeDTO.getEmployeeId());
        statement.setObject(2, employeeDTO.getName());
        statement.setObject(3, employeeDTO.getAddress());
        statement.setObject(4, employeeDTO.getPhoneNumber());
        statement.setObject(5, employeeDTO.getSalary());
        statement.setObject(6, employeeDTO.getUserName());

        return statement.executeUpdate() > 0;
    }
}

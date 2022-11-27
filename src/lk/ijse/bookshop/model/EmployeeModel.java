package lk.ijse.bookshop.model;

import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.to.Employee;
import lk.ijse.bookshop.util.CrudUtil;
import lk.ijse.bookshop.view.tm.EmployeeTm;
import lk.ijse.bookshop.view.tm.ItemTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeModel {
    public static ArrayList getAllDetails() throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM employee";
        ResultSet resultSet= CrudUtil.execute(sql);
        ArrayList arrayList=new ArrayList();
        while (resultSet.next()){
            arrayList.add(new EmployeeTm(resultSet.getString(1),resultSet.getString(2)
                    ,resultSet.getString(3),resultSet.getInt(4),resultSet.getDouble(5)));

        }
        return arrayList;
    }

    public static boolean updateSalary(String employeeId, double salary) throws SQLException, ClassNotFoundException {
        String sql="UPDATE employee SET Salary=? WHERE EmployeeId=?";
        return CrudUtil.execute(sql,salary,employeeId);
    }

    public static boolean deleteEmployee(String employeeId) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM employee WHERE EmployeeId=? ";
        return CrudUtil.execute(sql,employeeId);
    }


    public static String currentEmployeeId() throws SQLException, ClassNotFoundException {
        String sql="SELECT EmployeeId FROM employee ORDER BY EmployeeId DESC LIMIT 1";
        ResultSet resultSet= CrudUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    public static boolean updateTable(Employee employee) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DBConnection.getDBConnection().getConnection().prepareStatement("INSERT INTO employee values(?,?,?,?,?,?)");

        statement.setObject(1, employee.getEmployeeId());
        statement.setObject(2, employee.getName());
        statement.setObject(3, employee.getAddress());
        statement.setObject(4, employee.getPhoneNumber());
        statement.setObject(5, employee.getSalary());
        statement.setObject(6, employee.getUserName());

        return statement.executeUpdate() > 0;
    }
}

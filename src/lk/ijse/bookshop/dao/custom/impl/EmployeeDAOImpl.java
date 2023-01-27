package lk.ijse.bookshop.dao.custom.impl;

import lk.ijse.bookshop.dao.SQLUtil;
import lk.ijse.bookshop.dao.custom.EmployeeDAO;
import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.dto.EmployeeDTO;
import lk.ijse.bookshop.dto.OrderDetailDTO;
import lk.ijse.bookshop.entity.Employee;
import lk.ijse.bookshop.view.tm.EmployeeTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    public String getEmployeeId(String Username) throws SQLException, ClassNotFoundException {
        String sql="SELECT EmployeeId FROM employee WHERE Username=? ";
        ResultSet resultSet= SQLUtil.execute(sql,Username);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }
    public String getEmployeeName(String id) throws SQLException, ClassNotFoundException {
        String sql="SELECT Name FROM employee WHERE EmployeeId=?";
        ResultSet resultSet= SQLUtil.execute(sql,id);
        if (resultSet.next()){

            return resultSet.getString(1);
        }

        return null;
    }
    public ArrayList getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM employee";
        ResultSet resultSet = SQLUtil.execute(sql);
        ArrayList arrayList = new ArrayList();
        while (resultSet.next()) {
            arrayList.add(new EmployeeTm(resultSet.getString(1), resultSet.getString(2)
                    , resultSet.getString(3), resultSet.getInt(4), resultSet.getDouble(5)));

        }
        return arrayList;
    }

    public boolean update(Employee employee) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE employee SET Salary=?,Name=?,PhoneNumber=?,Address=? WHERE EmployeeId=?";
        return SQLUtil.execute(sql, employee.getSalary(),employee.getName(),employee.getPhoneNumber()
                ,employee.getAddress(),employee.getEmployeeId());
    }

    public boolean delete(String employeeId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM employee WHERE EmployeeId=? ";
        return SQLUtil.execute(sql, employeeId);
    }

    @Override
    public Employee search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }


    public String getId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT EmployeeId FROM employee ORDER BY EmployeeId DESC LIMIT 1";
        ResultSet resultSet = SQLUtil.execute(sql);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean saveO(String orderId, LocalDate orderDate, String customerId) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean saveD(OrderDetailDTO detail, String orderId) throws SQLException, ClassNotFoundException {
        return false;
    }

    public boolean insert(Employee employeeDTO) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DBConnection.getDBConnection().getConnection().prepareStatement("INSERT " +
                "INTO employee values(?,?,?,?,?,?)");

        statement.setObject(1, employeeDTO.getEmployeeId());
        statement.setObject(2, employeeDTO.getName());
        statement.setObject(3, employeeDTO.getAddress());
        statement.setObject(4, employeeDTO.getPhoneNumber());
        statement.setObject(5, employeeDTO.getSalary());
        statement.setObject(6, employeeDTO.getUserName());

        return statement.executeUpdate() > 0;
    }
}

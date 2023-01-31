package lk.ijse.bookshop.dao.custom;

import lk.ijse.bookshop.dao.CrudDAO;
import lk.ijse.bookshop.entity.Employee;

import java.sql.SQLException;

public interface EmployeeDAO extends CrudDAO<Employee>{
    String getEmployeeId(String Username) throws SQLException, ClassNotFoundException;
    String getEmployeeName(String id) throws SQLException, ClassNotFoundException;
}

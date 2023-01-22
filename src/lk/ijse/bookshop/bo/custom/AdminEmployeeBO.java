package lk.ijse.bookshop.bo.custom;


/*

   ` Coded By Isuru Dulakshana
   ` Date     1/19/2023 9:03 AM

*/


import lk.ijse.bookshop.dto.EmployeeDTO;
import lk.ijse.bookshop.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdminEmployeeBO extends SuperBO{
    ArrayList getAllDetails() throws SQLException, ClassNotFoundException;
    boolean deleteEmployee(String employeeId) throws SQLException, ClassNotFoundException;

    boolean updateSalary(EmployeeDTO employee) throws SQLException, ClassNotFoundException;

}

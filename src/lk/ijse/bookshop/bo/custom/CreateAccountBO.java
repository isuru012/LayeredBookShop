package lk.ijse.bookshop.bo.custom;


/*

   ` Coded By Isuru Dulakshana
   ` Date     1/19/2023 9:09 AM

*/


import lk.ijse.bookshop.dto.EmployeeDTO;
import lk.ijse.bookshop.dto.UserDTO;

import java.sql.SQLException;

public interface CreateAccountBO  extends SuperBO{
    String  currentEmployeeId() throws SQLException, ClassNotFoundException;
    boolean userAllDetailSave(UserDTO userDTO, EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;
}

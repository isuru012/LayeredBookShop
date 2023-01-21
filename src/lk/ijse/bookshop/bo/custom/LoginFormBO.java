package lk.ijse.bookshop.bo.custom;


/*

   ` Coded By Isuru Dulakshana
   ` Date     1/19/2023 9:10 AM

*/


import lk.ijse.bookshop.dto.UserDTO;

import java.sql.SQLException;

public interface LoginFormBO  extends SuperBO{
    UserDTO getLoginData(String username, String password) throws SQLException, ClassNotFoundException;
    String getEmployeeId(String Username) throws SQLException, ClassNotFoundException;
}

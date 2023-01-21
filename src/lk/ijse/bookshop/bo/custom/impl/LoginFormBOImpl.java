package lk.ijse.bookshop.bo.custom.impl;


/*

   ` Coded By Isuru Dulakshana
   ` Date     1/19/2023 9:10 AM

*/


import lk.ijse.bookshop.bo.custom.LoginFormBO;
import lk.ijse.bookshop.dao.DAOFactory;
import lk.ijse.bookshop.dao.custom.EmployeeDAO;
import lk.ijse.bookshop.dao.custom.UserDAO;
import lk.ijse.bookshop.dto.UserDTO;

import java.sql.SQLException;

public class LoginFormBOImpl implements LoginFormBO {
    UserDAO  userDAO= (UserDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.USER);
    EmployeeDAO employeeDAO= (EmployeeDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.EMPLOYEE);
    @Override
    public UserDTO getLoginData(String username, String password) throws SQLException, ClassNotFoundException {
        return userDAO.getLoginData(username,password);
    }

    @Override
    public String getEmployeeId(String Username) throws SQLException, ClassNotFoundException {
        return employeeDAO.getEmployeeId(Username);
    }
}

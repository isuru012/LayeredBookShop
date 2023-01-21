package lk.ijse.bookshop.bo.custom.impl;


/*

   ` Coded By Isuru Dulakshana
   ` Date     1/19/2023 9:09 AM

*/


import lk.ijse.bookshop.bo.custom.CreateAccountBO;
import lk.ijse.bookshop.dao.DAOFactory;
import lk.ijse.bookshop.dao.custom.EmployeeDAO;
import lk.ijse.bookshop.dao.custom.UserDAO;
import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.dto.EmployeeDTO;
import lk.ijse.bookshop.dto.UserDTO;
import lk.ijse.bookshop.entity.Employee;
import lk.ijse.bookshop.entity.User;
import lk.ijse.bookshop.model.EmployeeModel;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreateAccountBOImpl implements CreateAccountBO {

    EmployeeDAO employeeDAO= (EmployeeDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.EMPLOYEE);
   UserDAO userDAO= (UserDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.USER);
    @Override
    public String currentEmployeeId() throws SQLException, ClassNotFoundException {
        return employeeDAO.getId();
    }
    public boolean userAllDetailSave(UserDTO userDTO, EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDBConnection().getConnection().setAutoCommit(false);


            boolean isAddedOrder=userDAO.userDetails(new User(userDTO.getUserName(),userDTO.getPassword(),
                    userDTO.getRole()));
            if (isAddedOrder) {

                boolean updateEmployeeTable = employeeDAO.insert(new Employee(employeeDTO.getEmployeeId(),
                        employeeDTO.getName(),employeeDTO.getAddress(),employeeDTO.getPhoneNumber(),
                        employeeDTO.getSalary(),employeeDTO.getUserName()));
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

}

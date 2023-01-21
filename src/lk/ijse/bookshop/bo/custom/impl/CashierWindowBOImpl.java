package lk.ijse.bookshop.bo.custom.impl;


/*

   ` Coded By Isuru Dulakshana
   ` Date     1/19/2023 9:08 AM

*/


import lk.ijse.bookshop.bo.custom.CashierPlaceOrderBO;
import lk.ijse.bookshop.bo.custom.CashierWindowBO;
import lk.ijse.bookshop.dao.DAOFactory;
import lk.ijse.bookshop.dao.custom.CustomerDAO;
import lk.ijse.bookshop.dao.custom.EmployeeDAO;
import lk.ijse.bookshop.dto.CustomerDTO;
import lk.ijse.bookshop.entity.Customer;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class CashierWindowBOImpl  implements CashierWindowBO {
    EmployeeDAO employeeDAO= (EmployeeDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.EMPLOYEE);

    CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public String getEmployeeName(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.getEmployeeName(id);
    }

    @Override
    public ArrayList getAllDetails() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }

    @Override
    public String getOrderId() throws SQLException, ClassNotFoundException {
        return customerDAO.getId();
    }

    @Override
    public boolean insertCustomerData(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.insert(new Customer(customerDTO.getCusId(),customerDTO.getName(),
                customerDTO.getPhoneNumber(), (Date) customerDTO.getJoindDate(),customerDTO.getEmployeeId()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customerDTO.getCusId(),customerDTO.getName(),
                customerDTO.getPhoneNumber(), (Date) customerDTO.getJoindDate(),customerDTO.getEmployeeId()));
    }
}

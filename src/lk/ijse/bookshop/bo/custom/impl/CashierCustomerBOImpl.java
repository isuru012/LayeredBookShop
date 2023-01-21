package lk.ijse.bookshop.bo.custom.impl;

import lk.ijse.bookshop.bo.custom.CashierCustomerBO;
import lk.ijse.bookshop.dao.DAOFactory;
import lk.ijse.bookshop.dao.custom.CustomerDAO;
import lk.ijse.bookshop.dto.CustomerDTO;
import lk.ijse.bookshop.entity.Customer;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class CashierCustomerBOImpl implements CashierCustomerBO {
    CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.CUSTOMER);
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
        return customerDAO.insert(new Customer(customerDTO.getCusId(),customerDTO.getName(),customerDTO
                .getPhoneNumber(), (Date) customerDTO.getJoindDate(),customerDTO.getEmployeeId()));
    }

    @Override
    public boolean deleteCustomer(String CusId) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(CusId);
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customerDTO.getCusId(),customerDTO.getName(),customerDTO
                .getPhoneNumber(), (Date) customerDTO.getJoindDate(),customerDTO.getEmployeeId()));
    }
}

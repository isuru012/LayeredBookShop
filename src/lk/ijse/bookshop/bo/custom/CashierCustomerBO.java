package lk.ijse.bookshop.bo.custom;

import lk.ijse.bookshop.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CashierCustomerBO extends SuperBO {
    ArrayList getAllDetails() throws SQLException, ClassNotFoundException;
    String getOrderId() throws SQLException, ClassNotFoundException;
    boolean insertCustomerData(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String CusId) throws SQLException, ClassNotFoundException;
    boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

}

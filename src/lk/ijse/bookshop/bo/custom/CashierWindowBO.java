package lk.ijse.bookshop.bo.custom;


/*

   ` Coded By Isuru Dulakshana
   ` Date     1/19/2023 9:08 AM

*/


import lk.ijse.bookshop.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CashierWindowBO  extends SuperBO{
   String  getEmployeeName(String id) throws SQLException, ClassNotFoundException;
   ArrayList getAllDetails() throws SQLException, ClassNotFoundException;
   String getOrderId() throws SQLException, ClassNotFoundException;
   boolean insertCustomerData(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
   boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
   boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;


}

package lk.ijse.bookshop.bo.custom;


/*

   ` Coded By Isuru Dulakshana
   ` Date     1/19/2023 9:08 AM

*/


import lk.ijse.bookshop.dto.CusReloadDTO;
import lk.ijse.bookshop.dto.CustomerDTO;
import lk.ijse.bookshop.dto.ReloadDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CashierMakeReloadBO extends SuperBO {
    ArrayList getAllDetails() throws SQLException, ClassNotFoundException;
    String getOrderId() throws SQLException, ClassNotFoundException;
    ArrayList loadAllServiceProviders() throws SQLException, ClassNotFoundException;

    String getCusId() throws SQLException, ClassNotFoundException;

    boolean insertCustomerData(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    ReloadDTO searchDescription(String text) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    boolean placeReload(CusReloadDTO cusReloadDTO) throws SQLException, ClassNotFoundException;


}

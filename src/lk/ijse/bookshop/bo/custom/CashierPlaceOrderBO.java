package lk.ijse.bookshop.bo.custom;


/*

   ` Coded By Isuru Dulakshana
   ` Date     1/19/2023 9:08 AM

*/


import lk.ijse.bookshop.dao.custom.ItemDAO;
import lk.ijse.bookshop.dto.CusOrderDTO;
import lk.ijse.bookshop.dto.CustomerDTO;
import lk.ijse.bookshop.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CashierPlaceOrderBO extends SuperBO{
    ArrayList getAllDetails() throws SQLException, ClassNotFoundException;
    String getOrderId() throws SQLException, ClassNotFoundException;
    ArrayList loadAllDescriptionIds() throws SQLException, ClassNotFoundException;
    boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    String getCusId() throws SQLException, ClassNotFoundException;

    boolean insertCustomerData(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    boolean placeOrder(CusOrderDTO cusOrderDTO) throws SQLException, ClassNotFoundException;

    ItemDTO searchDescription(String text) throws SQLException, ClassNotFoundException;
    ArrayList getAllItemPrices(String itemId) throws SQLException, ClassNotFoundException;
    int getQtyTotalOfOneItem(String itemCode,double unitPrice) throws SQLException, ClassNotFoundException;
    int getItemQuantity(String itemId) throws SQLException, ClassNotFoundException;


    double getOfferAmount(String code, double sellingUnitPrice) throws SQLException, ClassNotFoundException;
}

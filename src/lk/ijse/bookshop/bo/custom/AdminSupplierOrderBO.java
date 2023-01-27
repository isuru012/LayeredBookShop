package lk.ijse.bookshop.bo.custom;


/*

   ` Coded By Isuru Dulakshana
   ` Date     1/19/2023 9:04 AM

*/


import lk.ijse.bookshop.dto.ItemDTO;
import lk.ijse.bookshop.dto.SupplierDTO;
import lk.ijse.bookshop.dto.SupplierOrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdminSupplierOrderBO extends SuperBO{
   String  getOrderId() throws SQLException, ClassNotFoundException;
   ArrayList loadAllSupplierNames() throws SQLException, ClassNotFoundException;
   ArrayList loadAllDescriptionIds() throws SQLException, ClassNotFoundException;
   int getItemQuantity(String itemCode) throws SQLException, ClassNotFoundException;

   double getSellingUnitPrice(String itemCode) throws SQLException, ClassNotFoundException;

   String getUserName() throws SQLException, ClassNotFoundException;

   boolean placeOrder(SupplierOrderDTO supplierOrderDTO) throws SQLException, ClassNotFoundException;

   SupplierDTO searchName(String text) throws SQLException, ClassNotFoundException;

   ItemDTO searchDescription(String text) throws SQLException, ClassNotFoundException;

   int getQtyTotalOfOneItem(String itemId) throws SQLException, ClassNotFoundException;


}

package lk.ijse.bookshop.bo.custom;


/*

   ` Coded By Isuru Dulakshana
   ` Date     1/19/2023 9:03 AM

*/


import lk.ijse.bookshop.dto.ItemDTO;
import lk.ijse.bookshop.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdminItemBO extends SuperBO {
    String getCurrentItemId() throws SQLException, ClassNotFoundException;
    ArrayList getAllDetailsForAdminItem() throws SQLException, ClassNotFoundException;
    boolean deleteItem( String itemId) throws SQLException, ClassNotFoundException;
    boolean insertItemData(ItemDTO item) throws SQLException, ClassNotFoundException;
    boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;


}

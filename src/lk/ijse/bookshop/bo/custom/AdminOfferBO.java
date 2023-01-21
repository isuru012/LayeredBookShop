package lk.ijse.bookshop.bo.custom;


/*

   ` Coded By Isuru Dulakshana
   ` Date     1/19/2023 9:04 AM

*/


import lk.ijse.bookshop.dto.OfferDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdminOfferBO extends SuperBO {
    ArrayList getAllDetails() throws SQLException, ClassNotFoundException;

    ArrayList loadAllItemNames() throws SQLException, ClassNotFoundException;

    ArrayList loadAllItemPrices(String id) throws SQLException, ClassNotFoundException;

    String getNowOfferId() throws SQLException, ClassNotFoundException;

    int getBatchNumber(Double sellingPrice, String itemCode) throws SQLException, ClassNotFoundException;

    boolean updateItemData(String itemCode, String code, int batchNumber) throws SQLException, ClassNotFoundException;

    boolean insertOfferData(OfferDTO offerDTO) throws SQLException, ClassNotFoundException;

    String getOfferId(String itemCode) throws SQLException, ClassNotFoundException;

    boolean deleteOffer(String itemCode) throws SQLException, ClassNotFoundException;

    boolean deleteOfferData(String offerId) throws SQLException, ClassNotFoundException;

    String getItemName(String itemId) throws SQLException, ClassNotFoundException;

    boolean updateOffer(OfferDTO offerDTO) throws SQLException, ClassNotFoundException;

    String getItemId(String itemName) throws SQLException, ClassNotFoundException;
}
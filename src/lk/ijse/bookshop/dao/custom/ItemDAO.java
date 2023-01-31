package lk.ijse.bookshop.dao.custom;

import lk.ijse.bookshop.dao.CrudDAO;
import lk.ijse.bookshop.dto.SupplierOrderDetailsDTO;
import lk.ijse.bookshop.entity.CusOrderDetails;
import lk.ijse.bookshop.entity.Item;
import lk.ijse.bookshop.entity.SupOrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<Item>{
    int getProductsAmount() throws SQLException, ClassNotFoundException;
    ArrayList loadAllDescriptionIds() throws SQLException, ClassNotFoundException;
    ArrayList loadAllItemPrices(String text) throws SQLException, ClassNotFoundException;
    int getBatchNumber(double text, String itemCode) throws SQLException, ClassNotFoundException;
    boolean updateItemData(String itemCode, String code, int batchNumber) throws SQLException, ClassNotFoundException;
    String getOfferId(String text) throws SQLException, ClassNotFoundException;
    boolean deleteOffer(String offerId) throws SQLException, ClassNotFoundException;
    String getDescription(String itemId) throws SQLException, ClassNotFoundException;
    String getItemId(String text) throws SQLException, ClassNotFoundException;
    boolean updateSupplierStock(ArrayList<SupOrderDetails> supplierOrderDetailsDTOS)
            throws SQLException, ClassNotFoundException;
    ArrayList getAllDetailsForAdminItem() throws SQLException, ClassNotFoundException;
    int getQtyTotalOfOneItem(String itemId, double unitPrice) throws SQLException, ClassNotFoundException;
    boolean updateStock(ArrayList<CusOrderDetails> orderDetailDTOS)
            throws SQLException, ClassNotFoundException;
    ArrayList getAllItemPrices(String ItemCode) throws SQLException, ClassNotFoundException;
    int getItemQuantity(String ItemCode) throws SQLException, ClassNotFoundException;
    boolean checkItem(ArrayList<SupOrderDetails> supplierOrderDetailsDTOS) throws SQLException
            , ClassNotFoundException;
    int getQtyTotalOfOneItem(String itemId) throws SQLException, ClassNotFoundException;
    double getSellingUnitPrice(String text) throws SQLException, ClassNotFoundException;

}

package lk.ijse.bookshop.bo.custom;


/*

   ` Coded By Isuru Dulakshana
   ` Date     1/19/2023 9:06 AM

*/


import lk.ijse.bookshop.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdminSupplierDetailsBO extends SuperBO {
    ArrayList getAllDetails() throws SQLException, ClassNotFoundException;
    boolean deleteSupplier(String SupId) throws SQLException, ClassNotFoundException;
    boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;
    String getSupplierIdFromNumber(String phoneNumber) throws SQLException, ClassNotFoundException;
    String getSupplierId() throws SQLException, ClassNotFoundException;
    String getUserName() throws SQLException, ClassNotFoundException;
    boolean insertSupplierData(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;

}

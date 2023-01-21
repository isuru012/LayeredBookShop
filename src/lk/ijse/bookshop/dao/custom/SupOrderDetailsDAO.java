package lk.ijse.bookshop.dao.custom;

import lk.ijse.bookshop.dao.CrudDAO;
import lk.ijse.bookshop.dto.SupplierOrderDetailsDTO;
import lk.ijse.bookshop.entity.Payment;
import lk.ijse.bookshop.entity.SupOrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupOrderDetailsDAO extends CrudDAO<SupOrderDetails>,SuperDAO {
    boolean updatePayment(String supplierId, String supOrderId) throws SQLException,
            ClassNotFoundException;
    boolean saveOrderDetails(ArrayList<SupOrderDetails> supplierOrderDetailsDTOS)
            throws SQLException, ClassNotFoundException;
}

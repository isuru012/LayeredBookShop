package lk.ijse.bookshop.dao.custom;

import lk.ijse.bookshop.dao.CrudDAO;
import lk.ijse.bookshop.dto.SupplierDTO;
import lk.ijse.bookshop.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierDAO  extends CrudDAO<Supplier>{
    Supplier search(String text) throws SQLException, ClassNotFoundException;
    ArrayList loadAllSupplierNames() throws SQLException, ClassNotFoundException;
    String getSupplierIdFromNumber(String valueOf) throws SQLException, ClassNotFoundException;
}

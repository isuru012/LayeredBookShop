package lk.ijse.bookshop.dao.custom;

import lk.ijse.bookshop.dao.CrudDAO;
import lk.ijse.bookshop.dto.CusReloadDetailsDTO;
import lk.ijse.bookshop.dto.ReloadDTO;
import lk.ijse.bookshop.entity.CusReloadDetails;
import lk.ijse.bookshop.entity.Payment;
import lk.ijse.bookshop.entity.Reload;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReloadDAO extends CrudDAO<Reload>{
    Reload search(String text) throws SQLException, ClassNotFoundException;
    ArrayList loadAllServiceProviders() throws SQLException, ClassNotFoundException;
    boolean updateAmount(ArrayList<CusReloadDetails> cusReloadDetailsDTOS)
            throws SQLException, ClassNotFoundException;

}

package lk.ijse.bookshop.dao.custom;

import lk.ijse.bookshop.dao.CrudDAO;
import lk.ijse.bookshop.entity.CusReloadDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CusReloadDetailsDAO extends CrudDAO<CusReloadDetails>{
    boolean saveReloadDetails(ArrayList<CusReloadDetails> cusReloadDetailsDTOS) throws SQLException, ClassNotFoundException;
}

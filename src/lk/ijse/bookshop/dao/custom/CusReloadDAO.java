package lk.ijse.bookshop.dao.custom;

import lk.ijse.bookshop.dao.CrudDAO;
import lk.ijse.bookshop.entity.CusReload;

import java.sql.SQLException;

public interface CusReloadDAO extends CrudDAO<CusReload>{
    boolean saveReload(CusReload cusReloadDTO) throws SQLException, ClassNotFoundException;
}

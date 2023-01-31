package lk.ijse.bookshop.dao.custom;

import lk.ijse.bookshop.dao.CrudDAO;
import lk.ijse.bookshop.entity.CusOrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CusOrderDetailsDAO extends CrudDAO<CusOrderDetails>{
    boolean saveOrderDetails(ArrayList<CusOrderDetails> orderDetailDTOS) throws SQLException, ClassNotFoundException;
}

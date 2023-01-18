package lk.ijse.bookshop.dao;

import lk.ijse.bookshop.dao.custom.SuperDAO;
import lk.ijse.bookshop.dto.OrderDetailDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO {
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    boolean insert(T customerDTO) throws SQLException, ClassNotFoundException;
    boolean update(T customerDTO) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    T search(String newValue) throws SQLException, ClassNotFoundException;
    String getId() throws SQLException, ClassNotFoundException;
    boolean saveO(String orderId, LocalDate orderDate, String customerId) throws SQLException, ClassNotFoundException;
    boolean saveD(OrderDetailDTO detail, String orderId) throws SQLException, ClassNotFoundException;
}

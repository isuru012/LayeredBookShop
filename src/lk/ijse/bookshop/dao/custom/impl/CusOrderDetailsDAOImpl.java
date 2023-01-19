package lk.ijse.bookshop.dao.custom.impl;

import lk.ijse.bookshop.dao.custom.CusOrderDetailsDAO;
import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.dto.OrderDetailDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class CusOrderDetailsDAOImpl implements CusOrderDetailsDAO {
    public boolean saveOrderDetails(ArrayList<OrderDetailDTO> orderDetailDTOS) throws SQLException, ClassNotFoundException {
        for (OrderDetailDTO ord : orderDetailDTOS) {
            if (!addOrderDetail(ord)) {
                return false;
            }
        }
        return true;
    }

    private boolean addOrderDetail(OrderDetailDTO ord) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DBConnection.getDBConnection().getConnection().prepareStatement("INSERT INTO cusorderdetails values(?,?,?,?,?)");

        statement.setObject(1, ord.getCusOrderId());
        statement.setObject(2, ord.getItemId());
        statement.setObject(3, ord.getUnitPrice());
        statement.setObject(4, ord.getQuantity());
        statement.setObject(5, ord.getTotalPrice());

        return statement.executeUpdate() > 0;
    }
}

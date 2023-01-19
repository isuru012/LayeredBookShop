package lk.ijse.bookshop.model;

import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.dto.OrderDetailDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailModel {
   /* public static boolean saveOrderDetails(ArrayList<OrderDetailDTO> orderDetailDTOS) throws SQLException, ClassNotFoundException {
        for (OrderDetailDTO ord : orderDetailDTOS) {
            if (!addOrderDetail(ord)) {
                return false;
            }
        }
        return true;
    }

    private static boolean addOrderDetail(OrderDetailDTO ord) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DBConnection.getDBConnection().getConnection().prepareStatement("INSERT INTO cusorderdetails values(?,?,?,?,?)");

        statement.setObject(1, ord.getCusOrderId());
        statement.setObject(2, ord.getItemId());
        statement.setObject(3, ord.getUnitPrice());
        statement.setObject(4, ord.getQuantity());
        statement.setObject(5, ord.getTotalPrice());

        return statement.executeUpdate() > 0;
    }*/
}

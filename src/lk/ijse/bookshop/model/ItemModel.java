package lk.ijse.bookshop.model;

import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.to.CustomerOrderDetail;
import lk.ijse.bookshop.util.CrudUtil;
import lk.ijse.bookshop.view.tm.CustomerTm;
import lk.ijse.bookshop.view.tm.ItemTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModel {
    public static boolean updateStock(ArrayList<CustomerOrderDetail> customerOrderDetails) throws SQLException, ClassNotFoundException {
        for (CustomerOrderDetail detail : customerOrderDetails) {
            if (!updateItem(detail)) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateItem(CustomerOrderDetail detail) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getDBConnection().getConnection().prepareStatement("UPDATE item SET QuantityOnHand=QuantityOnHand-? WHERE ItemId=?");
        stm.setObject(1, detail.getQuantity());
        stm.setObject(2, detail.getItemId());

        return stm.executeUpdate() > 0;
    }

    public static ArrayList getAllDetails() throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM item";
        ResultSet resultSet= CrudUtil.execute(sql);
        ArrayList arrayList=new ArrayList();
        while (resultSet.next()){
            arrayList.add(new ItemTm(resultSet.getString(1), resultSet.getInt(2),
                    resultSet.getString(3),resultSet.getDouble(5),resultSet.getInt(6),
                    resultSet.getString(7)));
        }
        return arrayList;
    }
}

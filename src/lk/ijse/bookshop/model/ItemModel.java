package lk.ijse.bookshop.model;

import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.to.CustomerOrderDetail;
import lk.ijse.bookshop.to.SupplierOrderDetail;
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
            if(resultSet.getString(7)==null){
                arrayList.add(new ItemTm(resultSet.getString(1), resultSet.getInt(2),
                        resultSet.getString(3),resultSet.getDouble(5),resultSet.getInt(6),
                        0));
            }else{
                String sql2="select Amount from offer where OfferId=? ";
                ResultSet resultSet1=CrudUtil.execute(sql2,resultSet.getString(7));
                if(resultSet1.next()) {
                    arrayList.add(new ItemTm(resultSet.getString(1), resultSet.getInt(2),
                            resultSet.getString(3), resultSet.getDouble(5), resultSet.getInt(6),
                            resultSet1.getDouble(1)));
                }
            }

        }
        return arrayList;
    }

    public static boolean updateSupplierStock(ArrayList<SupplierOrderDetail> supplierOrderDetails) throws SQLException, ClassNotFoundException {
        for (SupplierOrderDetail detail : supplierOrderDetails) {
            if (!updateSupplerItem(detail)) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateSupplerItem(SupplierOrderDetail detail) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getDBConnection().getConnection().prepareStatement("UPDATE item SET QuantityOnHand=QuantityOnHand+? WHERE ItemId=?");
        stm.setObject(1, detail.getQuantity());
        stm.setObject(2, detail.getItemId());

        return stm.executeUpdate() > 0;
    }
}

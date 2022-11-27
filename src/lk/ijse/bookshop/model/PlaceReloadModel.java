package lk.ijse.bookshop.model;

import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.to.CustomerReload;
import lk.ijse.bookshop.to.Item;
import lk.ijse.bookshop.to.Reload;
import lk.ijse.bookshop.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceReloadModel {

    public static Reload searchDescription(String text) throws SQLException, ClassNotFoundException {
        String searchText="%"+text+"%";
        String sql="SELECT * FROM reload WHERE ServiceProvider LIKE ?";
        ResultSet resultSet= CrudUtil.execute(sql,searchText);
        if (resultSet.next()){
            return new Reload(resultSet.getString(1), resultSet.getString(2),
                    resultSet.getDouble(3),resultSet.getDouble(4));

        }
        return null;
    }

    public static ArrayList loadAllServiceProviders() throws SQLException, ClassNotFoundException {
        String sql="SELECT ServiceProvider FROM reload";
        ResultSet resultSet=CrudUtil.execute(sql);
        ArrayList <String> arrayList=new ArrayList();
        while (resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        return arrayList;
    }

    public static String getOrderId() throws SQLException, ClassNotFoundException {
        String sql="SELECT CusReloadId FROM cusreload ORDER BY CusReloadId DESC LIMIT 1";
        ResultSet resultSet= CrudUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    public static boolean placeReload(CustomerReload customerReload) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDBConnection().getConnection().setAutoCommit(false);
            PreparedStatement statement=DBConnection.getDBConnection().getConnection().prepareStatement("INSERT INTO cusreload VALUES(?,?,?,?,?) ");
            statement.setObject(1,customerReload.getCusReloadId());
            statement.setObject(2,customerReload.getDate());
            statement.setObject(3,customerReload.getTime());
            statement.setObject(4,customerReload.getCusId());
            statement.setObject(5,customerReload.getEmployeeId());

            boolean isAddedOrder=statement.executeUpdate()>0;
            if (isAddedOrder){
                boolean saveReloadDetails=ReloadDetailModel.saveReloadDetails(customerReload.getCustomerReloadDetails());
                if (saveReloadDetails){
                    boolean updateAmount=ReloadModel.updateAmount(customerReload.getCustomerReloadDetails());
                    if (updateAmount){
                        DBConnection.getDBConnection().getConnection().commit();
                        return true;
                    }
                }
            }
            DBConnection.getDBConnection().getConnection().rollback();
            return false;

        }finally {
            DBConnection.getDBConnection().getConnection().setAutoCommit(true);
        }

    }
}

package lk.ijse.bookshop.dao.custom.impl;

import lk.ijse.bookshop.dao.SQLUtil;
import lk.ijse.bookshop.dao.custom.ReloadDAO;
import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.dto.CusReloadDetailsDTO;
import lk.ijse.bookshop.dto.OrderDetailDTO;
import lk.ijse.bookshop.dto.ReloadDTO;
import lk.ijse.bookshop.entity.CusReloadDetails;
import lk.ijse.bookshop.entity.Reload;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReloadDAOImpl implements ReloadDAO {

    @Override
    public ArrayList<Reload> getAll() throws SQLException, ClassNotFoundException {
       return null;
    }

    @Override
    public boolean insert(Reload customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Reload customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public Reload search(String text) throws SQLException, ClassNotFoundException {
        String searchText="%"+text+"%";
        String sql="SELECT * FROM reload WHERE ServiceProvider LIKE ?";
        ResultSet resultSet= SQLUtil.execute(sql,searchText);
        if (resultSet.next()){
            return new Reload(resultSet.getString(1), resultSet.getString(2),
                    resultSet.getDouble(3),resultSet.getDouble(4));

        }
        return null;
    }

    @Override
    public String getId() throws SQLException, ClassNotFoundException {
        String sql="SELECT ReloadId FROM reload ORDER BY ReloadId DESC LIMIT 1";
        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean saveO(String orderId, LocalDate orderDate, String customerId) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean saveD(OrderDetailDTO detail, String orderId) throws SQLException, ClassNotFoundException {
        return false;
    }

    public  ArrayList loadAllServiceProviders() throws SQLException, ClassNotFoundException {
        String sql="SELECT ServiceProvider FROM reload";
        ResultSet resultSet= SQLUtil.execute(sql);
        ArrayList <String> arrayList=new ArrayList();
        while (resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        return arrayList;
    }
    public boolean updateAmount(ArrayList<CusReloadDetails> cusReloadDetailsDTOS) throws SQLException, ClassNotFoundException {
        for (CusReloadDetails detail : cusReloadDetailsDTOS) {
            if (!updateAmmount(detail)) {
                return false;
            }
        }
        return true;
    }

    private boolean updateAmmount(CusReloadDetails detail) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getDBConnection().getConnection().
                prepareStatement("UPDATE reload SET ReloadAmount=ReloadAmount-? WHERE ReloadId=?");
        stm.setObject(1, detail.getTotalPrice());
        stm.setObject(2, detail.getReloadId());


        return stm.executeUpdate() > 0;
    }


}

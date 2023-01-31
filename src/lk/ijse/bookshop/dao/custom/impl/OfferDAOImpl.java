package lk.ijse.bookshop.dao.custom.impl;

import lk.ijse.bookshop.dao.SQLUtil;
import lk.ijse.bookshop.dao.custom.OfferDAO;
import lk.ijse.bookshop.dto.OfferDTO;
import lk.ijse.bookshop.dto.OrderDetailDTO;
import lk.ijse.bookshop.entity.CusOrder;
import lk.ijse.bookshop.entity.CusOrderDetails;
import lk.ijse.bookshop.entity.Offer;
import lk.ijse.bookshop.view.tm.EmployeeTm;
import lk.ijse.bookshop.view.tm.OfferTm;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class OfferDAOImpl implements OfferDAO {
    public  String getId() throws SQLException, ClassNotFoundException {
        String sql="SELECT OfferId FROM offer ORDER BY OfferId DESC LIMIT 1";
        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean saveO(CusOrder cusOrder) throws SQLException, ClassNotFoundException {
        return false;
    }



    @Override
    public ArrayList getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM offer";
        ResultSet resultSet = SQLUtil.execute(sql);
        ArrayList arrayList = new ArrayList();
        while (resultSet.next()) {
            arrayList.add(new OfferTm(resultSet.getString(1), 1
                    , resultSet.getDouble(2), resultSet.getDate(3), resultSet.getDate(4)));

        }
        return arrayList;
    }

    public  boolean insert(Offer offerDTO) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO offer VALUES (?,?,?,?)";
        return SQLUtil.execute(sql, offerDTO.getOfferId(), offerDTO.getAmmount(), offerDTO.getStartedDate(),
                offerDTO.getEndedDate());
    }
    public  boolean update(Offer offer) throws SQLException, ClassNotFoundException {
        String sql="UPDATE offer SET Amount=?,StartedDate=?,EndedDate=? WHERE OfferId=?";
        return SQLUtil.execute(sql,offer.getAmmount(),offer.getStartedDate(),offer.getEndedDate(),
                offer.getOfferId());
    }
    public  boolean delete(String offerId) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM offer WHERE OfferId=?";
        return SQLUtil.execute(sql,offerId);
    }

    @Override
    public Offer search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }
}

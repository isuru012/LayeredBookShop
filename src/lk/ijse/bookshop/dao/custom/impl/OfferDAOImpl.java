package lk.ijse.bookshop.dao.custom.impl;

import lk.ijse.bookshop.dao.SQLUtil;
import lk.ijse.bookshop.dao.custom.OfferDAO;
import lk.ijse.bookshop.dto.OfferDTO;
import lk.ijse.bookshop.view.tm.OfferTm;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OfferDAOImpl implements OfferDAO {
    public static String getNowOfferId() throws SQLException, ClassNotFoundException {
        String sql="SELECT OfferId FROM offer ORDER BY OfferId DESC LIMIT 1";
        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    public static boolean insertOfferData(OfferDTO offerDTO) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO offer VALUES (?,?,?,?)";
        return SQLUtil.execute(sql, offerDTO.getOfferId(), offerDTO.getAmount(), offerDTO.getStartDate(), offerDTO.getEndDate());
    }
    public static boolean updateOffer(String offerId, double offerAmount, Date startDate, Date endDate) throws SQLException, ClassNotFoundException {
        String sql="UPDATE offer SET Amount=?,StartedDate=?,EndedDate=? WHERE OfferId=?";
        return SQLUtil.execute(sql,offerAmount,startDate,endDate,offerId);
    }
    public static boolean deleteOfferData(String offerId) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM offer WHERE OfferId=?";
        return SQLUtil.execute(sql,offerId);
    }
}

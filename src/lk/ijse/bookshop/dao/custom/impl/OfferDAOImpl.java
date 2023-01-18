package lk.ijse.bookshop.dao.custom.impl;

import lk.ijse.bookshop.dao.SQLUtil;
import lk.ijse.bookshop.dto.OfferDTO;
import lk.ijse.bookshop.view.tm.OfferTm;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OfferDAOImpl {
    public ArrayList loadAllItemNames() throws SQLException, ClassNotFoundException {
        String sql = "SELECT Description FROM item GROUP BY Description";
        ResultSet resultSet = SQLUtil.execute(sql);
        ArrayList<String> arrayList = new ArrayList();
        while (resultSet.next()) {
            arrayList.add(resultSet.getString(1));
        }
        return arrayList;
    }

    public ArrayList loadAllItemPrices(String text) throws SQLException, ClassNotFoundException {
        String sql = "SELECT SellingUnitPrice FROM item WHERE Description=?";
        ResultSet resultSet = SQLUtil.execute(sql, text);
        ArrayList<String> arrayList = new ArrayList();
        while (resultSet.next()) {
            arrayList.add(resultSet.getString(1));
        }
        return arrayList;
    }

    public ArrayList getAllDetails() throws SQLException, ClassNotFoundException {
        String sql = "SELECT item.ItemId,item.BatchNumber,offer.Amount,offer.StartedDate," +
                "offer.EndedDate FROM item LEFT JOIN offer ON item.OfferId = offer.OfferId ORDER BY ItemId";
        ResultSet resultSet = SQLUtil.execute(sql);
        ArrayList arrayList = new ArrayList();
        while (resultSet.next()) {
            arrayList.add(new OfferTm(resultSet.getString(1), resultSet.getInt(2),
                    resultSet.getDouble(3), resultSet.getDate(4), resultSet.getDate(5)));

        }
        return arrayList;


    }

    public String getItemId(String text) throws SQLException, ClassNotFoundException {
        String sql = "SELECT ItemId FROM item WHERE Description=?";
        ResultSet resultSet = SQLUtil.execute(sql, text);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public int getBatchNumber(double text, String itemCode) throws SQLException, ClassNotFoundException {
        String sql = "SELECT BatchNumber FROM item WHERE ItemId=? AND SellingUnitPrice=?";
        ResultSet resultSet = SQLUtil.execute(sql, itemCode, text);
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return -1;
    }

    public String getNowOfferId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT OfferId FROM offer ORDER BY OfferId DESC LIMIT 1";
        ResultSet resultSet = SQLUtil.execute(sql);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public boolean insertOfferData(OfferDTO offerDTO) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO offer VALUES (?,?,?,?)";
        return SQLUtil.execute(sql, offerDTO.getOfferId(), offerDTO.getAmount(), offerDTO.getStartDate(), offerDTO.getEndDate());
    }

    public String getItemName(String text) throws SQLException, ClassNotFoundException {
        String sql = "SELECT Description FROM item WHERE ItemId=?";
        ResultSet resultSet = SQLUtil.execute(sql, text);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public String getOfferId(String text) throws SQLException, ClassNotFoundException {
        String sql = "SELECT OfferId FROM item WHERE ItemId=?";
        ResultSet resultSet = SQLUtil.execute(sql, text);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public boolean updateOffer(String offerId, double offerAmount, Date startDate, Date endDate) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE offer SET Amount=?,StartedDate=?,EndedDate=? WHERE OfferId=?";
        return SQLUtil.execute(sql, offerAmount, startDate, endDate, offerId);
    }

    public boolean deleteOffer(String offerId) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE item SET OfferId=?  WHERE ItemId=?";
        return SQLUtil.execute(sql, null, offerId);
    }

    public boolean updateItemData(String itemCode, String code, int batchNumber) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE item SET OfferId=? WHERE ItemId=? AND BatchNumber=?";
        return SQLUtil.execute(sql, code, itemCode, batchNumber);

    }

    public boolean deleteOfferData(String offerId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM offer WHERE OfferId=?";
        return SQLUtil.execute(sql, offerId);
    }
}

package lk.ijse.bookshop.model;

import lk.ijse.bookshop.to.Offer;
import lk.ijse.bookshop.util.CrudUtil;
import lk.ijse.bookshop.view.tm.OfferTm;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OfferModel {

    public static ArrayList loadAllItemNames() throws SQLException, ClassNotFoundException {
        String sql="SELECT Description FROM item GROUP BY Description";
        ResultSet resultSet=CrudUtil.execute(sql);
        ArrayList <String> arrayList=new ArrayList();
        while (resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        return arrayList;
    }

    public static ArrayList loadAllItemPrices(String text) throws SQLException, ClassNotFoundException {
        String sql="SELECT SellingUnitPrice FROM item WHERE Description=?";
        ResultSet resultSet=CrudUtil.execute(sql,text);
        ArrayList <String> arrayList=new ArrayList();
        while (resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        return arrayList;
    }

    public static ArrayList getAllDetails() throws SQLException, ClassNotFoundException {
        String sql="SELECT item.ItemId,item.BatchNumber,offer.Amount,offer.StartedDate," +
                "offer.EndedDate FROM item LEFT JOIN offer ON item.OfferId = offer.OfferId ORDER BY ItemId";
        ResultSet resultSet=CrudUtil.execute(sql);
        ArrayList arrayList=new ArrayList();
        while (resultSet.next()){
        arrayList.add(new OfferTm(resultSet.getString(1), resultSet.getInt(2),
        resultSet.getDouble(3),resultSet.getDate(4),resultSet.getDate(5)));

        }
        return arrayList;


    }

    public static String getItemId(String text) throws SQLException, ClassNotFoundException {
        String sql="SELECT ItemId FROM item WHERE Description=?";
        ResultSet resultSet=CrudUtil.execute(sql,text);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    public static int getBatchNumber(double text, String itemCode) throws SQLException, ClassNotFoundException {
        String sql="SELECT BatchNumber FROM item WHERE ItemId=? AND SellingUnitPrice=?";
        ResultSet resultSet=CrudUtil.execute(sql,itemCode,text);
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return -1;
    }

    public static String getNowOfferId() throws SQLException, ClassNotFoundException {
        String sql="SELECT OfferId FROM offer ORDER BY OfferId DESC LIMIT 1";
        ResultSet resultSet=CrudUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    public static boolean insertOfferData(Offer offer) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO offer VALUES (?,?,?,?)";
        return CrudUtil.execute(sql,offer.getOfferId(),offer.getAmount(),offer.getStartDate(),offer.getEndDate());
    }

    public static String getItemName(String text) throws SQLException, ClassNotFoundException {
        String sql="SELECT Description FROM item WHERE ItemId=?";
        ResultSet resultSet=CrudUtil.execute(sql,text);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    public static String getOfferId(String text) throws SQLException, ClassNotFoundException {
        String sql="SELECT OfferId FROM item WHERE ItemId=?";
        ResultSet resultSet=CrudUtil.execute(sql,text);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    public static boolean updateOffer(String offerId, double offerAmount, Date startDate, Date endDate) throws SQLException, ClassNotFoundException {
        String sql="UPDATE offer SET Amount=?,StartedDate=?,EndedDate=? WHERE OfferId=?";
        return CrudUtil.execute(sql,offerAmount,startDate,endDate,offerId);
    }

    public static boolean deleteOffer(String offerId) throws SQLException, ClassNotFoundException {
        String sql="UPDATE item SET OfferId=?  WHERE ItemId=?";
        return CrudUtil.execute(sql,null,offerId);
    }

    public static boolean updateItemData(String itemCode, String code, int batchNumber) throws SQLException, ClassNotFoundException {
        String sql="UPDATE item SET OfferId=? WHERE ItemId=? AND BatchNumber=?";
        return CrudUtil.execute(sql,code,itemCode,batchNumber);

    }

    public static boolean deleteOfferData(String offerId) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM offer WHERE OfferId=?";
        return CrudUtil.execute(sql,offerId);
    }
}

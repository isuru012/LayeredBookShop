package lk.ijse.bookshop.dao.custom.impl;

import lk.ijse.bookshop.dao.SQLUtil;
import lk.ijse.bookshop.dao.custom.QueryDAO;
import lk.ijse.bookshop.view.tm.OfferTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    public ArrayList getAllDetailsFromOffers() throws SQLException, ClassNotFoundException {
        String sql="SELECT item.ItemId,item.BatchNumber,offer.Amount,offer.StartedDate," +
                "offer.EndedDate FROM item LEFT JOIN offer ON item.OfferId = offer.OfferId ORDER BY ItemId";
        ResultSet resultSet= SQLUtil.execute(sql);
        ArrayList arrayList=new ArrayList();
        while (resultSet.next()){
            arrayList.add(new OfferTm(resultSet.getString(1), resultSet.getInt(2),
                    resultSet.getDouble(3),resultSet.getDate(4),resultSet.getDate(5)));

        }
        return arrayList;


    }

    public  ArrayList getTrendingItems() throws SQLException, ClassNotFoundException {
        String sql="select Description from item right join cusorderdetails  on item.ItemId = cusorderdetails.ItemId group by cusorderdetails.ItemId order by SUM(Quantity) desc";
        ResultSet resultSet= SQLUtil.execute(sql);
        ArrayList<String> arrayList=new ArrayList<>();
        while (resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        return arrayList;
    }

    public  double getMonday() throws SQLException, ClassNotFoundException {
        String sql="SELECT SUM(TotalPrice) AS tot FROM cusorderdetails LEFT JOIN  cusorder  on\n" +
                "        cusorder.CusOrderId = cusorderdetails.CusOrderId where DAYNAME(Date)='Monday' group by DAYNAME(Date)";

        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getDouble(1);
        }
        return -1;
    }

    public  double getTuesday() throws SQLException, ClassNotFoundException {
        String sql="SELECT SUM(TotalPrice) AS tot FROM cusorderdetails LEFT JOIN  cusorder  on\n" +
                "        cusorder.CusOrderId = cusorderdetails.CusOrderId where DAYNAME(Date)='Tuesday' group by DAYNAME(Date)";

        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getDouble(1);
        }
        return -1;
    }

    public  double getWednesday() throws SQLException, ClassNotFoundException {
        String sql="SELECT SUM(TotalPrice) AS tot FROM cusorderdetails LEFT JOIN  cusorder  on\n" +
                "        cusorder.CusOrderId = cusorderdetails.CusOrderId where DAYNAME(Date)='Wednesday' group by DAYNAME(Date)";

        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getDouble(1);
        }
        return -1;
    }

    public  double getThursday() throws SQLException, ClassNotFoundException {
        String sql="SELECT SUM(TotalPrice) AS tot FROM cusorderdetails LEFT JOIN  cusorder  on\n" +
                "        cusorder.CusOrderId = cusorderdetails.CusOrderId where DAYNAME(Date)='Thursday' group by DAYNAME(Date)";

        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getDouble(1);
        }
        return -1;
    }

    public  double getFriday() throws SQLException, ClassNotFoundException {
        String sql="SELECT SUM(TotalPrice) AS tot FROM cusorderdetails LEFT JOIN  cusorder  on\n" +
                "        cusorder.CusOrderId = cusorderdetails.CusOrderId where DAYNAME(Date)='Friday' group by DAYNAME(Date)";

        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getDouble(1);
        }
        return -1;
    }

    public  double getSaturday() throws SQLException, ClassNotFoundException {
        String sql="SELECT SUM(TotalPrice) AS tot FROM cusorderdetails LEFT JOIN  cusorder  on\n" +
                "        cusorder.CusOrderId = cusorderdetails.CusOrderId where DAYNAME(Date)='Saturday' group by DAYNAME(Date)";

        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getDouble(1);
        }
        return -1;
    }

    public  double getSunday() throws SQLException, ClassNotFoundException {
        String sql="SELECT SUM(TotalPrice) AS tot FROM cusorderdetails LEFT JOIN  cusorder  on\n" +
                "        cusorder.CusOrderId = cusorderdetails.CusOrderId where DAYNAME(Date)='Sunday' group by DAYNAME(Date)";

        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getDouble(1);
        }
        return -1;
    }

}

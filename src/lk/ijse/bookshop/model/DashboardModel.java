package lk.ijse.bookshop.model;

import lk.ijse.bookshop.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DashboardModel {

    public static int getOrdersAmount() throws SQLException, ClassNotFoundException {
        String sql="SELECT COUNT(CusOrderId) FROM cusorder";
        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return -1;
    }

    public static int getProductsAmount() throws SQLException, ClassNotFoundException {
        String sql="SELECT COUNT(ItemId) FROM item";
        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return -1;
    }

    public static int getCustomersAmount() throws SQLException, ClassNotFoundException {
        String sql="SELECT COUNT(CusId) FROM customer";
        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return -1;
    }

    public static ArrayList getTrendingItems() throws SQLException, ClassNotFoundException {
        String sql="select Description from item right join cusorderdetails  on item.ItemId = cusorderdetails.ItemId group by cusorderdetails.ItemId order by SUM(Quantity) desc";
        ResultSet resultSet= SQLUtil.execute(sql);
        ArrayList<String> arrayList=new ArrayList<>();
        while (resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        return arrayList;
    }

    public static double getMonday() throws SQLException, ClassNotFoundException {
        String sql="SELECT SUM(TotalPrice) AS tot FROM cusorderdetails LEFT JOIN  cusorder  on\n" +
                "        cusorder.CusOrderId = cusorderdetails.CusOrderId where DAYNAME(Date)='Monday' group by DAYNAME(Date)";

        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getDouble(1);
        }
        return -1;
    }

    public static double getTuesday() throws SQLException, ClassNotFoundException {
        String sql="SELECT SUM(TotalPrice) AS tot FROM cusorderdetails LEFT JOIN  cusorder  on\n" +
                "        cusorder.CusOrderId = cusorderdetails.CusOrderId where DAYNAME(Date)='Tuesday' group by DAYNAME(Date)";

        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getDouble(1);
        }
        return -1;
    }

    public static double getWednesday() throws SQLException, ClassNotFoundException {
        String sql="SELECT SUM(TotalPrice) AS tot FROM cusorderdetails LEFT JOIN  cusorder  on\n" +
                "        cusorder.CusOrderId = cusorderdetails.CusOrderId where DAYNAME(Date)='Wednesday' group by DAYNAME(Date)";

        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getDouble(1);
        }
        return -1;
    }

    public static double getThursday() throws SQLException, ClassNotFoundException {
        String sql="SELECT SUM(TotalPrice) AS tot FROM cusorderdetails LEFT JOIN  cusorder  on\n" +
                "        cusorder.CusOrderId = cusorderdetails.CusOrderId where DAYNAME(Date)='Thursday' group by DAYNAME(Date)";

        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getDouble(1);
        }
        return -1;
    }

    public static double getFriday() throws SQLException, ClassNotFoundException {
        String sql="SELECT SUM(TotalPrice) AS tot FROM cusorderdetails LEFT JOIN  cusorder  on\n" +
                "        cusorder.CusOrderId = cusorderdetails.CusOrderId where DAYNAME(Date)='Friday' group by DAYNAME(Date)";

        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getDouble(1);
        }
        return -1;
    }

    public static double getSaturday() throws SQLException, ClassNotFoundException {
        String sql="SELECT SUM(TotalPrice) AS tot FROM cusorderdetails LEFT JOIN  cusorder  on\n" +
                "        cusorder.CusOrderId = cusorderdetails.CusOrderId where DAYNAME(Date)='Saturday' group by DAYNAME(Date)";

        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getDouble(1);
        }
        return -1;
    }

    public static double getSunday() throws SQLException, ClassNotFoundException {
        String sql="SELECT SUM(TotalPrice) AS tot FROM cusorderdetails LEFT JOIN  cusorder  on\n" +
                "        cusorder.CusOrderId = cusorderdetails.CusOrderId where DAYNAME(Date)='Sunday' group by DAYNAME(Date)";

        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getDouble(1);
        }
        return -1;
    }

}

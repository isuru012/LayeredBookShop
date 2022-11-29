package lk.ijse.bookshop.model;

import lk.ijse.bookshop.util.CrudUtil;
import lk.ijse.bookshop.view.tm.EmployeeTm;
import lk.ijse.bookshop.view.tm.ExpenditureTm;
import lk.ijse.bookshop.view.tm.PaymentTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExpenditureModel {

    public static ArrayList getAllDetails() throws SQLException, ClassNotFoundException {
        String sql="select * from expenditure";
        ResultSet resultSet= CrudUtil.execute(sql);
        ArrayList arrayList=new ArrayList();
        while (resultSet.next()){
            arrayList.add(new ExpenditureTm(resultSet.getString(1),resultSet.getString(2),resultSet.getTime(5),
                    resultSet.getDate(4),resultSet.getDouble(3)));
        }
        return arrayList;

    }

    public static String getCurrentId() throws SQLException, ClassNotFoundException {
        String sql="SELECT ExpenditureId FROM expenditure ORDER BY ExpenditureId DESC LIMIT 1";
        ResultSet resultSet= CrudUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }
}

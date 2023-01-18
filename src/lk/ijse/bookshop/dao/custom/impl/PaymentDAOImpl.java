package lk.ijse.bookshop.dao.custom.impl;

import lk.ijse.bookshop.dao.SQLUtil;
import lk.ijse.bookshop.view.tm.PaymentTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl {
    public String generateCurrentPaymentId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT PaymentId FROM payment ORDER BY PaymentId DESC LIMIT 1";
        ResultSet resultSet = SQLUtil.execute(sql);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public ArrayList getAllDetails() throws SQLException, ClassNotFoundException {
        /*String sql="select bookshop.payment.PaymentId,bookshop.payment.Amount,bookshop.payment.Date,\n" +
                "       bookshop.payment.Time,bookshop.supplier.Name,\n" +
                "       bookshop.payment.ExpenditureId from supplier INNER JOIN payment\n" +
                "WHERE SupplierId=(select SupplierId from suporder where SupOrderId=\n" +
                "        (select SupOrderId from payment where SupOrderId=?))";*/
        String sql = "select * from payment";
        ResultSet resultSet = SQLUtil.execute(sql);
        ArrayList arrayList = new ArrayList();
        while (resultSet.next()) {
            arrayList.add(new PaymentTm(resultSet.getString(1), resultSet.getDouble(2),
                    resultSet.getDate(3), resultSet.getTime(4),
                    resultSet.getString(6), resultSet.getString(7)));
        }
        return arrayList;
    }
}

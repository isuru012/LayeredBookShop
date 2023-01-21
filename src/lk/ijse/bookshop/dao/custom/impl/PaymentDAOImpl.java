package lk.ijse.bookshop.dao.custom.impl;

import lk.ijse.bookshop.dao.SQLUtil;
import lk.ijse.bookshop.dao.custom.PaymentDAO;
import lk.ijse.bookshop.dto.OrderDetailDTO;
import lk.ijse.bookshop.entity.Payment;
import lk.ijse.bookshop.view.tm.PaymentTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    public String getId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT PaymentId FROM payment ORDER BY PaymentId DESC LIMIT 1";
        ResultSet resultSet = SQLUtil.execute(sql);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public ArrayList getAll() throws SQLException, ClassNotFoundException {
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



    @Override
    public boolean insert(Payment customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Payment customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Payment search(String newValue) throws SQLException, ClassNotFoundException {
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
}

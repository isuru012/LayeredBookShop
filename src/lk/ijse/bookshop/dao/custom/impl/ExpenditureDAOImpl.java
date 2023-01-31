package lk.ijse.bookshop.dao.custom.impl;

import lk.ijse.bookshop.dao.DAOFactory;
import lk.ijse.bookshop.dao.SQLUtil;
import lk.ijse.bookshop.dao.custom.ExpenditureDAO;
import lk.ijse.bookshop.dao.custom.PaymentDAO;
import lk.ijse.bookshop.dao.custom.UserDAO;
import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.dto.ExpenditureDTO;
import lk.ijse.bookshop.dto.OrderDetailDTO;
import lk.ijse.bookshop.dto.PaymentDTO;
import lk.ijse.bookshop.entity.CusOrder;
import lk.ijse.bookshop.entity.CusOrderDetails;
import lk.ijse.bookshop.entity.Expenditure;
import lk.ijse.bookshop.view.tm.ExpenditureTm;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class ExpenditureDAOImpl implements ExpenditureDAO {
    PaymentDAO paymentDAO= (PaymentDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.PAYMENT);
    UserDAO userDAO= (UserDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.USER);
    public boolean updatePayment(String expenditureId) throws SQLException, ClassNotFoundException {
        String paymentId=generateNextPaymentId(paymentDAO.getId());

        double amount=0;

        String sql="SELECT Amount FROM Expenditure WHERE expenditureId=?";
        ResultSet resultSet= SQLUtil.execute(sql,expenditureId);

        if (resultSet.next()){
            amount=resultSet.getDouble(1);
        }

        Date date = Date.valueOf(LocalDate.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        Time time = Time.valueOf(simpleDateFormat.format(new java.util.Date()));

        String username= userDAO.getUserName();

        PaymentDTO paymentDTO =new PaymentDTO(paymentId,amount,date,time,username,null,expenditureId);

        PreparedStatement statement= DBConnection.getDBConnection().getConnection().prepareStatement(
                "INSERT INTO payment VALUES(?,?,?,?,?,?,?) ");
        statement.setObject(1, paymentDTO.getPaymentId());
        statement.setObject(2, paymentDTO.getAmount());
        statement.setObject(3, paymentDTO.getDate());
        statement.setObject(4, paymentDTO.getTime());
        statement.setObject(5, paymentDTO.getUsername());
        statement.setObject(6, null);
        statement.setObject(7,paymentDTO.getExpenditureId());

        return statement.executeUpdate()>0;
    }
    public String generateNextPaymentId(String generateCurrentPaymentId) {
        if (generateCurrentPaymentId != null) {
            String[] split = generateCurrentPaymentId.split("P");
            int id = Integer.parseInt(split[1]);

            id += 1;
            if (id>=10){
                return "P000" + id;
            }else if(id>=100){
                return "P00" +id;
            }else if(id>=1000){
                return "P0"+id;
            }else if(id>=10000){
                return "P"+id;
            }else{

                return "P0000" + id;
            }
        }
        return "P00001";
    }
    public ArrayList getAll() throws SQLException, ClassNotFoundException {
        String sql = "select * from expenditure";
        ResultSet resultSet = SQLUtil.execute(sql);
        ArrayList arrayList = new ArrayList();
        while (resultSet.next()) {
            arrayList.add(new ExpenditureTm(resultSet.getString(1), resultSet.getString(2), resultSet.getTime(5),
                    resultSet.getDate(4), resultSet.getDouble(3)));
        }
        return arrayList;

    }

    public String getId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT ExpenditureId FROM expenditure ORDER BY ExpenditureId DESC LIMIT 1";
        ResultSet resultSet = SQLUtil.execute(sql);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean saveO(CusOrder cusOrder) throws SQLException, ClassNotFoundException {
        return false;
    }


    public boolean insert(Expenditure expenditure) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO expenditure VALUES (?,?,?,?,?,?)";
        return SQLUtil.execute(sql, expenditure.getExpenditureId(), expenditure.getDescription(),
                expenditure.getAmount(), expenditure.getDate(),
                expenditure.getTime(), expenditure.getUserName());
    }


    public boolean update(Expenditure expenditure) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE  expenditure SET Description=?,Amount=? WHERE ExpenditureId=?";
        return SQLUtil.execute(sql, expenditure.getDescription(), expenditure.getAmount(),
                expenditure.getExpenditureId());
    }

    public boolean delete(String expenditureId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM expenditure WHERE ExpenditureId=?";
        return SQLUtil.execute(sql, expenditureId);
    }

    @Override
    public Expenditure search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }
}

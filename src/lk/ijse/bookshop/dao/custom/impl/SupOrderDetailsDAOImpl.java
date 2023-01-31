package lk.ijse.bookshop.dao.custom.impl;

import lk.ijse.bookshop.dao.DAOFactory;
import lk.ijse.bookshop.dao.SQLUtil;
import lk.ijse.bookshop.dao.custom.PaymentDAO;
import lk.ijse.bookshop.dao.custom.SupOrderDetailsDAO;
import lk.ijse.bookshop.dao.custom.SupplierDAO;
import lk.ijse.bookshop.dao.custom.UserDAO;
import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.dto.OrderDetailDTO;
import lk.ijse.bookshop.dto.PaymentDTO;
import lk.ijse.bookshop.dto.SupplierOrderDetailsDTO;
import lk.ijse.bookshop.entity.CusOrder;
import lk.ijse.bookshop.entity.CusOrderDetails;
import lk.ijse.bookshop.entity.SupOrderDetails;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class SupOrderDetailsDAOImpl implements SupOrderDetailsDAO {
    PaymentDAO paymentDAO= (PaymentDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.PAYMENT);
    UserDAO userDAO= (UserDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.USER);
    public boolean updatePayment(String supplierId, String supOrderId) throws SQLException, ClassNotFoundException {
        String paymentId=generateNextPaymentId(paymentDAO.getId());

        double amount=0;

        String sql="SELECT SUM(suporderdetails.TotalPrice) FROM suporderdetails WHERE SupOrderId=?";
        ResultSet resultSet= SQLUtil.execute(sql,supOrderId);

        if (resultSet.next()){
            amount=resultSet.getDouble(1);
        }

        Date date = Date.valueOf(LocalDate.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        Time time = Time.valueOf(simpleDateFormat.format(new java.util.Date()));

        String username= userDAO.getUserName();

        PaymentDTO paymentDTO =new PaymentDTO(paymentId,amount,date,time,username,supOrderId,null);

        PreparedStatement statement=DBConnection.getDBConnection().getConnection().prepareStatement("INSERT INTO payment VALUES(?,?,?,?,?,?,?) ");
        statement.setObject(1, paymentDTO.getPaymentId());
        statement.setObject(2, paymentDTO.getAmount());
        statement.setObject(3, paymentDTO.getDate());
        statement.setObject(4, paymentDTO.getTime());
        statement.setObject(5, paymentDTO.getUsername());
        statement.setObject(6, paymentDTO.getSupplierOrderId());
        statement.setObject(7,null);

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
    public boolean saveOrderDetails(ArrayList<SupOrderDetails> supplierOrderDetailsDTOS) throws SQLException, ClassNotFoundException {
        for (SupOrderDetails ord : supplierOrderDetailsDTOS) {
            if (!addOrderDetail(ord)) {
                return false;
            }
        }
        return true;
    }

    private boolean addOrderDetail(SupOrderDetails ord) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DBConnection.getDBConnection().getConnection().
                prepareStatement("INSERT INTO suporderdetails values(?,?,?,?,?)");

        statement.setObject(1, ord.getSupOrderId());
        statement.setObject(2, ord.getItemId());
        statement.setObject(3, ord.getUnitPrice());
        statement.setObject(4, ord.getQuantity());
        statement.setObject(5, ord.getTotalPrice());

        return statement.executeUpdate() > 0;
    }

    @Override
    public ArrayList<SupOrderDetails> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean insert(SupOrderDetails customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(SupOrderDetails customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public SupOrderDetails search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveO(CusOrder cusOrder) throws SQLException, ClassNotFoundException {
        return false;
    }


}

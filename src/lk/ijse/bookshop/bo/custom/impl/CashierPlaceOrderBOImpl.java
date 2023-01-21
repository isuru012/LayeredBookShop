package lk.ijse.bookshop.bo.custom.impl;


/*

   ` Coded By Isuru Dulakshana
   ` Date     1/19/2023 9:08 AM

*/


import lk.ijse.bookshop.bo.custom.CashierPlaceOrderBO;
import lk.ijse.bookshop.dao.DAOFactory;
import lk.ijse.bookshop.dao.custom.*;
import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.dto.*;
import lk.ijse.bookshop.entity.*;
import lk.ijse.bookshop.model.ItemModel;
import lk.ijse.bookshop.model.OrderDetailModel;
import lk.ijse.bookshop.model.SupplierOrderDetailModel;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class CashierPlaceOrderBOImpl implements CashierPlaceOrderBO {
    CusOrderDAO cusOrderDAO= (CusOrderDAO) DAOFactory.getDAOFactory().getDAOTypes
            (DAOFactory.DAOTypes.CUSORDER);
    CusOrderDetailsDAO cusOrderDetailsDAO= (CusOrderDetailsDAO) DAOFactory.getDAOFactory().
            getDAOTypes(DAOFactory.DAOTypes.CUSORDERDETAILS);
    ItemDAO itemDAO= (ItemDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.ITEM);
    CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public ArrayList getAllDetails() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }

    @Override
    public String getOrderId() throws SQLException, ClassNotFoundException {
        return cusOrderDAO.getId();
    }

    @Override
    public ArrayList loadAllDescriptionIds() throws SQLException, ClassNotFoundException {
        return itemDAO.loadAllDescriptionIds();
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customerDTO.getCusId(),customerDTO.getName(),customerDTO
                .getPhoneNumber(), (Date) customerDTO.getJoindDate(),customerDTO.getEmployeeId()));
    }

    @Override
    public String getCusId() throws SQLException, ClassNotFoundException {
        return customerDAO.getId();
    }

    @Override
    public boolean insertCustomerData(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.insert(new Customer(customerDTO.getCusId(),customerDTO.getName(),customerDTO
                .getPhoneNumber(), (Date) customerDTO.getJoindDate(),customerDTO.getEmployeeId()));
    }

    public  boolean placeOrder(CusOrderDTO cusOrderDTO) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDBConnection().getConnection().setAutoCommit(false);


            boolean isAddedOrder=cusOrderDAO.saveCusOrder(new CusOrder(cusOrderDTO.getCusOrderId(),
                    cusOrderDTO.getDate(),cusOrderDTO.getTime(),cusOrderDTO.getCusId(),cusOrderDTO.
                    getEmployeeId()));
            if (isAddedOrder){

                ArrayList<OrderDetailDTO> detailDTOS = cusOrderDTO.getCustomerOrderDetails();
                ArrayList<CusOrderDetails> cusOrders = new ArrayList<>();
                for (OrderDetailDTO i : detailDTOS) {
                    cusOrders.add(new CusOrderDetails(i.getCusOrderId(),i.getItemId(),i.getUnitPrice(),
                            i.getQuantity(),i.getTotalPrice()
                    ));
                }
                boolean saveOrderDetails= cusOrderDetailsDAO.saveOrderDetails(cusOrders);
                if (saveOrderDetails){
                    boolean updateStock= itemDAO.updateStock(cusOrders);
                    if (updateStock){
                        DBConnection.getDBConnection().getConnection().commit();
                        return true;
                    }
                }
            }
            DBConnection.getDBConnection().getConnection().rollback();
            return false;

        }finally {
            DBConnection.getDBConnection().getConnection().setAutoCommit(true);
        }
    }

    @Override
    public ItemDTO searchDescription(String text) throws SQLException, ClassNotFoundException {
        Item item=itemDAO.search(text);
        return new ItemDTO(item.getItemId(),item.getBatchNumber(),item.getDescription(),
                item.getBuyingUnitPrice(),item.getSellingUnitPrice(),item.getQuantityOnHand(),
                item.getOfferId());
    }

    @Override
    public ArrayList getAllItemPrices(String itemId) throws SQLException, ClassNotFoundException {
        return itemDAO.getAllItemPrices(itemId);
    }

    @Override
    public int getQtyTotalOfOneItem(String itemCode, double unitPrice) throws SQLException, ClassNotFoundException {
        return itemDAO.getQtyTotalOfOneItem(itemCode,unitPrice);
    }

    @Override
    public int getItemQuantity(String itemId) throws SQLException, ClassNotFoundException {
        return itemDAO.getItemQuantity(itemId);
    }

}

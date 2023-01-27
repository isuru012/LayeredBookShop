package lk.ijse.bookshop.bo.custom.impl;


/*

   ` Coded By Isuru Dulakshana
   ` Date     1/19/2023 9:04 AM

*/


import lk.ijse.bookshop.bo.custom.AdminSupplierOrderBO;
import lk.ijse.bookshop.dao.DAOFactory;
import lk.ijse.bookshop.dao.custom.*;
import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.dto.ItemDTO;
import lk.ijse.bookshop.dto.SupplierDTO;
import lk.ijse.bookshop.dto.SupplierOrderDTO;
import lk.ijse.bookshop.dto.SupplierOrderDetailsDTO;
import lk.ijse.bookshop.entity.Item;
import lk.ijse.bookshop.entity.SupOrder;
import lk.ijse.bookshop.entity.SupOrderDetails;
import lk.ijse.bookshop.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminSupplierOrderBOImpl implements AdminSupplierOrderBO {
    SupOrderDAO saveOrderAdd= (SupOrderDAO) DAOFactory.getDAOFactory().
            getDAOTypes(DAOFactory.DAOTypes.SUPORDER);
    SupOrderDetailsDAO supOrderDetailsDAO= (SupOrderDetailsDAO) DAOFactory.getDAOFactory().
            getDAOTypes(DAOFactory.DAOTypes.SUPORDERDETAILS);
    ItemDAO itemDAO= (ItemDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.ITEM);

    SupplierDAO supplierDAO= (SupplierDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.SUPPLIER);

    UserDAO userDAO= (UserDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.USER);

    @Override
    public String getOrderId() throws SQLException, ClassNotFoundException {
        return saveOrderAdd.getId();
    }

    @Override
    public ArrayList loadAllSupplierNames() throws SQLException, ClassNotFoundException {
        return supplierDAO.loadAllSupplierNames();
    }

    @Override
    public ArrayList loadAllDescriptionIds() throws SQLException, ClassNotFoundException {
        return itemDAO.loadAllDescriptionIds();
    }

    @Override
    public int getItemQuantity(String itemCode) throws SQLException, ClassNotFoundException {
        return itemDAO.getItemQuantity(itemCode);
    }

    @Override
    public double getSellingUnitPrice(String itemCode) throws SQLException, ClassNotFoundException {
        return itemDAO.getSellingUnitPrice(itemCode);
    }

    @Override
    public String getUserName() throws SQLException, ClassNotFoundException {
        return userDAO.getUserName();
    }

    public boolean placeOrder(SupplierOrderDTO supplierOrderDTO) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDBConnection().getConnection().setAutoCommit(false);

            boolean isAddedOrder = saveOrderAdd.saveOrder(new SupOrder(supplierOrderDTO.getSupOrderId(),
                    supplierOrderDTO.getDate(), supplierOrderDTO.getTime(), supplierOrderDTO.getSupplierId(),
                    supplierOrderDTO.getUserName()));
            if (isAddedOrder) {
                ArrayList<SupplierOrderDetailsDTO> supplierOrderDetailsDTOS = supplierOrderDTO.getSupplierOrderDetails();
                ArrayList<SupOrderDetails> supOrderDetails = new ArrayList<>();
                for (SupplierOrderDetailsDTO i : supplierOrderDetailsDTOS) {
                    supOrderDetails.add(new SupOrderDetails(i.getSupOrderId(),
                            i.getItemId(), i.getUnitPrice(), i.getQuantity(), i.getTotalPrice()
                    ));
                }
                boolean saveOrderDetails = supOrderDetailsDAO.saveOrderDetails(supOrderDetails);
                if (saveOrderDetails) {

                    boolean updateStock = itemDAO.checkItem(supOrderDetails);
                    if (updateStock) {
                        boolean updatePayment = supOrderDetailsDAO.updatePayment(supplierOrderDTO.getSupplierId(),
                                supplierOrderDTO.getSupOrderId());
                        if (updatePayment) {
                            DBConnection.getDBConnection().getConnection().commit();
                            return true;
                        }
                    }
                }
            }
            DBConnection.getDBConnection().getConnection().rollback();
            return false;

        } finally {
            DBConnection.getDBConnection().getConnection().setAutoCommit(true);
        }
    }

    @Override
    public SupplierDTO searchName(String text) throws SQLException, ClassNotFoundException {
        Supplier supplier=supplierDAO.search(text);

        return new SupplierDTO(supplier.getSupplierId(),supplier.getName(),supplier.getLocation(),
                supplier.getPhoneNumber(),supplier.getUserName());
    }

    @Override
    public ItemDTO searchDescription(String text) throws SQLException, ClassNotFoundException {
        Item item=itemDAO.search(text);
        return new ItemDTO(item.getItemId(),item.getBatchNumber(),item.getDescription(),
                item.getBuyingUnitPrice(),item.getSellingUnitPrice(),item.getQuantityOnHand(),
                item.getOfferId());
    }

    @Override
    public int getQtyTotalOfOneItem(String itemId) throws SQLException, ClassNotFoundException {
        return itemDAO.getQtyTotalOfOneItem(itemId);
    }
}

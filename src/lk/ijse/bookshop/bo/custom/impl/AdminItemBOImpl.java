package lk.ijse.bookshop.bo.custom.impl;


/*

   ` Coded By Isuru Dulakshana
   ` Date     1/19/2023 9:03 AM

*/


import lk.ijse.bookshop.bo.custom.AdminItemBO;
import lk.ijse.bookshop.dao.DAOFactory;
import lk.ijse.bookshop.dao.custom.ItemDAO;
import lk.ijse.bookshop.dto.ItemDTO;
import lk.ijse.bookshop.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminItemBOImpl implements AdminItemBO {
    ItemDAO itemDAO= (ItemDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.ITEM);
    @Override
    public String getCurrentItemId() throws SQLException, ClassNotFoundException {
        return itemDAO.getId();
    }

    @Override
    public ArrayList getAllDetailsForAdminItem() throws SQLException, ClassNotFoundException {
        /*ArrayList<ItemDTO> itemDTOS=new ArrayList<>();
        ArrayList<Item> items= itemDAO.getAll();
        for (Item i:items) {
            itemDTOS.add(new ItemDTO(i.getItemId(),i.getBatchNumber(),i.getDescription(),i.getBuyingUnitPrice(),i.getSellingUnitPrice(),i.getQuantityOnHand(),
                    i.getOfferId()));
        }
        return itemDTOS;*/
return itemDAO.getAll();
    }

    @Override
    public boolean deleteItem(String itemId) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(itemId);
    }

    @Override
    public boolean insertItemData(ItemDTO item) throws SQLException, ClassNotFoundException {
        return itemDAO.insert(new Item(item.getItemId(),item.getBatchNumber(),item.getDescription(),item.getBuyingUnitPrice(),item.getSellingUnitPrice(),
                item.getQuantityOnHand(),item.getOfferId()));
    }

    @Override
    public boolean updateItem(ItemDTO item) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(item.getItemId(),item.getBatchNumber(),item.getDescription(),item.getBuyingUnitPrice(),item.getSellingUnitPrice(),
                item.getQuantityOnHand(),item.getOfferId()));
    }
}

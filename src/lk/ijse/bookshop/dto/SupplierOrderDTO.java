package lk.ijse.bookshop.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class SupplierOrderDTO {
    private String SupOrderId;
    private Date  date;
    private Time time;
    private String SupplierId;
    private String userName;
    private ArrayList<SupplierOrderDetailsDTO> supplierOrderDetailsDTOS;

    public SupplierOrderDTO() {
    }

    public SupplierOrderDTO(String supOrderId, Date date, Time time, String supplierId,
                            String userName, ArrayList<SupplierOrderDetailsDTO> supplierOrderDetailsDTOS) {
        SupOrderId = supOrderId;
        this.date = date;
        this.time = time;
        SupplierId = supplierId;
        this.userName = userName;
        this.supplierOrderDetailsDTOS = supplierOrderDetailsDTOS;
    }

    public String getSupOrderId() {
        return SupOrderId;
    }

    public void setSupOrderId(String supOrderId) {
        SupOrderId = supOrderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getSupplierId() {
        return SupplierId;
    }

    public void setSupplierId(String supplierId) {
        SupplierId = supplierId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<SupplierOrderDetailsDTO> getSupplierOrderDetails() {
        return supplierOrderDetailsDTOS;
    }

    public void setSupplierOrderDetails(ArrayList<SupplierOrderDetailsDTO> supplierOrderDetailsDTOS) {
        this.supplierOrderDetailsDTOS = supplierOrderDetailsDTOS;
    }
}

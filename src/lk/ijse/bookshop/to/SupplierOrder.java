package lk.ijse.bookshop.to;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class SupplierOrder {
    private String SupOrderId;
    private Date  date;
    private Time time;
    private String SupplierId;
    private String userName;
    private ArrayList<SupplierOrderDetail> supplierOrderDetails;

    public SupplierOrder() {
    }

    public SupplierOrder(String supOrderId, Date date, Time time, String supplierId,
                         String userName, ArrayList<SupplierOrderDetail> supplierOrderDetails) {
        SupOrderId = supOrderId;
        this.date = date;
        this.time = time;
        SupplierId = supplierId;
        this.userName = userName;
        this.supplierOrderDetails = supplierOrderDetails;
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

    public ArrayList<SupplierOrderDetail> getSupplierOrderDetails() {
        return supplierOrderDetails;
    }

    public void setSupplierOrderDetails(ArrayList<SupplierOrderDetail> supplierOrderDetails) {
        this.supplierOrderDetails = supplierOrderDetails;
    }
}

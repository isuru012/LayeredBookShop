package lk.ijse.bookshop.entity;

import java.sql.Date;
import java.sql.Time;

public class SupOrder {
    private String supOrderId;
    private Date date;
    private Time time;
    private String supplierId;
    private String userName;

    public SupOrder() {
    }

    public SupOrder(String supOrderId, Date date, Time time, String supplierId, String userName) {
        this.supOrderId = supOrderId;
        this.date = date;
        this.time = time;
        this.supplierId = supplierId;
        this.userName = userName;
    }

    public String getSupOrderId() {
        return supOrderId;
    }

    public void setSupOrderId(String supOrderId) {
        this.supOrderId = supOrderId;
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
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "SupOrder{" +
                "supOrderId='" + supOrderId + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", supplierId='" + supplierId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}

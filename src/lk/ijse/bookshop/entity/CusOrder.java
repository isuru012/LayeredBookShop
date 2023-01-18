package lk.ijse.bookshop.entity;

import java.sql.Date;
import java.sql.Time;

public class CusOrder {
    private String cusOrderId;
    private Date date;
    private Time time;
    private String cusId;
    private String employeeId;

    public CusOrder() {
    }

    public CusOrder(String cusOrderId, Date date, Time time, String cusId, String employeeId) {
        this.cusOrderId = cusOrderId;
        this.date = date;
        this.time = time;
        this.cusId = cusId;
        this.employeeId = employeeId;
    }

    public String getCusOrderId() {
        return cusOrderId;
    }

    public void setCusOrderId(String cusOrderId) {
        this.cusOrderId = cusOrderId;
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

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "CusOrder{" +
                "cusOrderId='" + cusOrderId + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", cusId='" + cusId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                '}';
    }
}

package lk.ijse.bookshop.to;


import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class CustomerOrder {
    private String cusOrderId;
    private Date date;
    private Time time;
    private String cusId;
    private String employeeId;
    private ArrayList<CustomerOrderDetail> customerOrderDetails;

    public CustomerOrder() {
    }

    public CustomerOrder(String cusOrderId, Date date, Time time, String cusId, String employeeId, ArrayList<CustomerOrderDetail> customerOrderDetails) {
        this.cusOrderId = cusOrderId;
        this.date = date;
        this.time = time;
        this.cusId = cusId;
        this.employeeId = employeeId;
        this.customerOrderDetails = customerOrderDetails;
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

    public ArrayList<CustomerOrderDetail> getCustomerOrderDetails() {
        return customerOrderDetails;
    }

    public void setCustomerOrderDetails(ArrayList<CustomerOrderDetail> customerOrderDetails) {
        this.customerOrderDetails = customerOrderDetails;
    }
}

package lk.ijse.bookshop.to;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class CustomerReload {
    private String cusReloadId;
    private Date date;
    private Time time;
    private String cusId;
    private String EmployeeId;

    private ArrayList<CustomerReloadDetail> customerReloadDetails= new ArrayList<>();

    public CustomerReload() {
    }

    public CustomerReload(String cusReloadId, Date date, Time time, String cusId, String employeeId, ArrayList<CustomerReloadDetail> customerReloadDetails) {
        this.cusReloadId = cusReloadId;
        this.date = date;
        this.time = time;
        this.cusId = cusId;
        EmployeeId = employeeId;
        this.customerReloadDetails = customerReloadDetails;
    }

    public String getCusReloadId() {
        return cusReloadId;
    }

    public void setCusReloadId(String cusReloadId) {
        this.cusReloadId = cusReloadId;
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
        return EmployeeId;
    }

    public void setEmployeeId(String employeeId) {
        EmployeeId = employeeId;
    }

    public ArrayList<CustomerReloadDetail> getCustomerReloadDetails() {
        return customerReloadDetails;
    }

    public void setCustomerReloadDetails(ArrayList<CustomerReloadDetail> customerReloadDetails) {
        this.customerReloadDetails = customerReloadDetails;
    }
}

package lk.ijse.bookshop.view.tm;

public class EmployeeTm {
    private String employeeId;
    private String name;
    private String address;
    private int phoneNumber;
    private double salary;

    public EmployeeTm() {
    }

    public EmployeeTm(String employeeId, String name, String address, int phoneNumber, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

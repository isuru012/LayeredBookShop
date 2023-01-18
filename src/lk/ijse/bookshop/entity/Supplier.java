package lk.ijse.bookshop.entity;

public class Supplier {
    private String supplierId;
    private String name;
    private String location;
    private int phoneNumber;
    private String userName;

    public Supplier() {
    }

    public Supplier(String supplierId, String name, String location, int phoneNumber, String userName) {
        this.supplierId = supplierId;
        this.name = name;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId='" + supplierId + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", userName='" + userName + '\'' +
                '}';
    }
}

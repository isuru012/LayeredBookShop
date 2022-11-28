package lk.ijse.bookshop.view.tm;

public class SupplierDetailTm {
  private String Name;
  private int phoneNumber;
  private String address;
  private int totalOrders;

    public SupplierDetailTm() {
    }

    public SupplierDetailTm(String name, int phoneNumber, String address, int totalOrders) {
        Name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.totalOrders = totalOrders;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }
}

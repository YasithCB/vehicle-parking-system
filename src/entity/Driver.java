package entity;

public class Driver {
    private String name;
    private String nic;
    private String drivingLicenseNumber;
    private String address;
    private String contactNum;
    private boolean available;


    public Driver() {
    }

    public Driver(String name, String nic, String drivingLicenseNumber, String address, String contactNum) {
        this.name = name;
        this.nic = nic;
        this.drivingLicenseNumber = drivingLicenseNumber;
        this.address = address;
        this.contactNum = contactNum;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return name;
    }
}

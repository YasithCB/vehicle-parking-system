package entity;

public class Vehicle {
    private String vehicleNumber;
    private String vehicleType;
    private int maxWeight;
    private int noOfPassengers;
    private String driverName;
    private String parkedTime;
    private String leaveTime;
    private int slot;

    public Vehicle() {
    }

    public Vehicle(String vehicleNumber, String vehicleType, int maxWeight, int noOfPassengers, String parkedTime, String leaveTime,int slot) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.maxWeight = maxWeight;
        this.noOfPassengers = noOfPassengers;
        this.parkedTime = parkedTime;
        this.leaveTime = leaveTime;
        this.slot = slot;
    }

    public Vehicle(String vehicleNumber, String vehicleType, int maxWeight, int noOfPassengers, String driverName, String leaveTime) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.maxWeight = maxWeight;
        this.noOfPassengers = noOfPassengers;
        this.driverName = driverName;
        this.leaveTime = leaveTime;
    }

    public Vehicle(String vehicleNumber, String vehicleType, int maxWeight, int noOfPassengers) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.maxWeight = maxWeight;
        this.noOfPassengers = noOfPassengers;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getNoOfPassengers() {
        return noOfPassengers;
    }

    public void setNoOfPassengers(int noOfPassengers) {
        this.noOfPassengers = noOfPassengers;
    }

    public String getdriverName() {
        return driverName;
    }

    public void setdriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getParkedTime() {
        return parkedTime;
    }

    public void setParkedTime(String parkedTime) {
        this.parkedTime = parkedTime;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    @Override
    public String toString() {
        return vehicleNumber;
    }
}

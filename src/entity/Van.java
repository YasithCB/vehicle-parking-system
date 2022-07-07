package entity;

public class Van extends Vehicle{

    public Van() {
    }

    public Van(String vehicleNumber, String vehicleType, int maxWeight, int noOfPassengers, String parkedTime, String leaveTime, int slot) {
        super(vehicleNumber, vehicleType, maxWeight, noOfPassengers, parkedTime, leaveTime, slot);
    }

    public Van(String vehicleNumber, String vehicleType, int maxWeight, int noOfPassengers, String driverName, String leaveTime) {
        super(vehicleNumber, vehicleType, maxWeight, noOfPassengers, driverName, leaveTime);
    }

    public Van(String vehicleNumber, String vehicleType, int maxWeight, int noOfPassengers) {
        super(vehicleNumber, vehicleType, maxWeight, noOfPassengers);
    }

}

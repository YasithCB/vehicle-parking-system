package entity;

public class Bus extends Vehicle{

    public Bus() {
    }

    public Bus(String vehicleNumber, String vehicleType, int maxWeight, int noOfPassengers, String parkedTime, String leaveTime, int slot) {
        super(vehicleNumber, vehicleType, maxWeight, noOfPassengers, parkedTime, leaveTime, slot);
    }

    public Bus(String vehicleNumber, String vehicleType, int maxWeight, int noOfPassengers, String driverName, String leaveTime) {
        super(vehicleNumber, vehicleType, maxWeight, noOfPassengers, driverName, leaveTime);
    }

    public Bus(String vehicleNumber, String vehicleType, int maxWeight, int noOfPassengers) {
        super(vehicleNumber, vehicleType, maxWeight, noOfPassengers);
    }

}
